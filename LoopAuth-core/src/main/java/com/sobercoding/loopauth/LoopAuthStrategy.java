package com.sobercoding.loopauth;


import com.sobercoding.loopauth.config.LoopAuthConfig;
import com.sobercoding.loopauth.context.LoopAuthContext;
import com.sobercoding.loopauth.context.LoopAuthContextDefaultImpl;
import com.sobercoding.loopauth.dao.LoopAuthDao;
import com.sobercoding.loopauth.dao.LoopAuthDaoImpl;
import com.sobercoding.loopauth.face.component.LoopAuthLogin;
import com.sobercoding.loopauth.face.component.LoopAuthPermission;
import com.sobercoding.loopauth.face.component.LoopAuthToken;
import com.sobercoding.loopauth.function.LrFunction;
import com.sobercoding.loopauth.function.PolicyFun;
import com.sobercoding.loopauth.model.TokenModel;
import com.sobercoding.loopauth.permission.PermissionInterface;
import com.sobercoding.loopauth.permission.PermissionInterfaceDefImpl;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * LoopAuth  Bean管理器
 * @author: Sober
 */

public class LoopAuthStrategy {

    /**
     * 配置文件
     */
    private volatile static LoopAuthConfig loopAuthConfig;

    public static void setLoopAuthConfig(LoopAuthConfig loopAuthConfig) {
        LoopAuthStrategy.loopAuthConfig = loopAuthConfig;
    }

    public static LoopAuthConfig getLoopAuthConfig() {
        if (LoopAuthStrategy.loopAuthConfig == null){
            synchronized(LoopAuthStrategy.class){
                if (LoopAuthStrategy.loopAuthConfig == null){
                    setLoopAuthConfig(new LoopAuthConfig());
                }
            }
        }
        return LoopAuthStrategy.loopAuthConfig;
    }

    /**
     * Token策略
     */
    private volatile static LoopAuthToken loopAuthToken;

    public static void setLoopAuthToken(LoopAuthToken loopAuthToken) {
        LoopAuthStrategy.loopAuthToken = loopAuthToken;
    }

    public static LoopAuthToken getLoopAuthToken() {
        if (LoopAuthStrategy.loopAuthToken == null){
            synchronized(LoopAuthStrategy.class){
                if (LoopAuthStrategy.loopAuthToken == null){
                    setLoopAuthToken(new LoopAuthToken());
                }
            }
        }
        return LoopAuthStrategy.loopAuthToken;
    }

    /**
     * 验证权限策略
     */
    private volatile static LoopAuthPermission loopAuthPermission;

    public static void setLoopAuthPermission(LoopAuthPermission loopAuthPermission) {
        LoopAuthStrategy.loopAuthPermission = loopAuthPermission;
    }

    public static LoopAuthPermission getLoopAuthPermission() {
        if (LoopAuthStrategy.loopAuthPermission == null){
            synchronized(LoopAuthStrategy.class){
                if (LoopAuthStrategy.loopAuthPermission == null){
                    setLoopAuthPermission(new LoopAuthPermission());
                }
            }
        }
        return LoopAuthStrategy.loopAuthPermission;
    }


    private volatile static LoopAuthLogin loopAuthLogin;

    public static void setLoopAuthLogin(LoopAuthLogin loopAuthLogin) {
        LoopAuthStrategy.loopAuthLogin = loopAuthLogin;
    }

    public static LoopAuthLogin getLoopAuthLogin() {
        if (LoopAuthStrategy.loopAuthLogin == null){
            synchronized(LoopAuthStrategy.class){
                if (LoopAuthStrategy.loopAuthLogin == null){
                    setLoopAuthLogin(new LoopAuthLogin());
                }
            }
        }
        return LoopAuthStrategy.loopAuthLogin;
    }

    /**
     * 会话缓存操作
     */
    private volatile static LoopAuthDao loopAuthDao;

    public static void setLoopAuthDao(LoopAuthDao loopAuthDao) {
        LoopAuthStrategy.loopAuthDao = loopAuthDao;
    }

    public static LoopAuthDao getLoopAuthDao() {
        if (LoopAuthStrategy.loopAuthDao == null){
            synchronized(LoopAuthStrategy.class){
                if (LoopAuthStrategy.loopAuthDao == null){
                    setLoopAuthDao(new LoopAuthDaoImpl());
                }
            }
        }
        return LoopAuthStrategy.loopAuthDao;
    }

    /**
     * 上下文Context Bean
     */
    private volatile static LoopAuthContext loopAuthContext;
    public static void setLoopAuthContext(LoopAuthContext loopAuthContext) {
        LoopAuthStrategy.loopAuthContext = loopAuthContext;
    }
    public static LoopAuthContext getLoopAuthContext() {
        if (loopAuthContext == null) {
            synchronized (LoopAuthStrategy.class) {
                if (loopAuthContext == null) {
                    setLoopAuthContext(new LoopAuthContextDefaultImpl());
                }
            }
        }
        return loopAuthContext;
    }


    /**
     * 权限认证 Bean 获取角色/权限代码
     */
    private volatile static PermissionInterface permissionInterface;
    public static void setPermissionInterface(PermissionInterface permissionInterface) {
        LoopAuthStrategy.permissionInterface = permissionInterface;
    }
    public static PermissionInterface getPermissionInterface() {
        if (permissionInterface == null) {
            synchronized (LoopAuthStrategy.class) {
                if (permissionInterface == null) {
                    setPermissionInterface(new PermissionInterfaceDefImpl());
                }
            }
        }
        return permissionInterface;
    }

    /**
     * ABAC鉴权匹配方式
     */
    private volatile static Map<String, PolicyFun> policyFunMap;

    /**
     * 写入ABAC自定义匹配方法
     * @param key
     * @param policyFun
     */
    public static void setPolicyFun(String key, PolicyFun policyFun) {
        policyFunMap.put(key, policyFun);
    }

    public static PolicyFun getPolicyFunMap(String key) {
        return policyFunMap.get(key);
    }


    /**
     * 获取盐默认方法
     */
    public static Function<String,String> getSecretKey = userId -> LoopAuthStrategy
            .getLoopAuthConfig().getSecretKey();

    /**
     * 默认登录规则处理
     * 新的tokenModel直接加入tokenModels返回需要删除的列表
     */
    public static LrFunction<Set<TokenModel>, TokenModel> loginRulesMatching = (tokenModels, tokenModel) -> {
        if (tokenModels.contains(tokenModel)){
            return new HashSet<>();
        }
        Set<TokenModel> removeTokenModels = new HashSet<>();
        // 开启token共生
        if (LoopAuthStrategy.getLoopAuthConfig().getMutualism()){
            // 同端互斥开启  删除相同端的登录
            if (LoopAuthStrategy.getLoopAuthConfig().getExclusion()){
                // 查看是否有同端口
                Optional<TokenModel> optionalTokenModel = tokenModels.stream()
                        .filter(item -> item.getFacility().equals(tokenModel.getFacility()))
                        .findAny();
                // 对tokenModels,removeTokenModels操作
                if (optionalTokenModel.isPresent()){
                    tokenModels.remove(optionalTokenModel.get());
                    removeTokenModels.add(optionalTokenModel.get());
                }
            }
            // 登录数量超出限制  删除较早的会话
            int maxLoginCount = LoopAuthStrategy.getLoopAuthConfig().getMaxLoginCount();
            if (maxLoginCount != -1 && tokenModels.size() >= maxLoginCount){
                List<TokenModel> tokenModelList = new ArrayList<>(tokenModels);
                // 排序
                tokenModelList = tokenModelList.stream()
                        .sorted(Comparator.comparing(TokenModel::getCreateTime))
                        .collect(Collectors.toList());
                // 获得早期会话
                tokenModelList = tokenModelList.stream()
                        .limit(tokenModels.size() - maxLoginCount + 1)
                        .collect(Collectors.toList());
                // 对tokenModels,removeTokenModels操作
                tokenModelList.forEach(tokenModels::remove);
                removeTokenModels.addAll(tokenModelList);
            }
        }else {
            // 未开启token共生  清除所有会话
            removeTokenModels.addAll(tokenModels);
        }
        // 添加新的会话
        tokenModels.add(tokenModel);
        return removeTokenModels;
    };


}
