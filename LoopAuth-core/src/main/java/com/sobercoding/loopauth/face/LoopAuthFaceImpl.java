package com.sobercoding.loopauth.face;

import com.sobercoding.loopauth.LoopAuthStrategy;
import com.sobercoding.loopauth.face.component.LoopAuthLogin;
import com.sobercoding.loopauth.face.component.LoopAuthPermission;
import com.sobercoding.loopauth.face.component.LoopAuthToken;
import com.sobercoding.loopauth.model.TokenModel;
import com.sobercoding.loopauth.model.UserSession;
import com.sobercoding.loopauth.model.constant.LoopAuthVerifyMode;

/**
 * 开放接口
 * @author: Sober
 */
public class LoopAuthFaceImpl {

    public static LoopAuthLogin LOOP_AUTH_LOGIN = LoopAuthStrategy.getLoopAuthLogin();

    public static LoopAuthToken LOOP_AUTH_TOKEN = LoopAuthStrategy.getLoopAuthToken();

    public static LoopAuthPermission LOOP_AUTH_PERMISSION = LoopAuthStrategy.getLoopAuthPermission();

    /**
     * 登录, 设置终端
     * @author Sober
     * @param loginId 用户Id
     */
    public static void login(String loginId) {
        LOOP_AUTH_LOGIN.login(
                new TokenModel()
                        .setLoginId(loginId)
                        .setCreateTime(System.currentTimeMillis())
                        .setTimeOut(LoopAuthStrategy.getLoopAuthConfig().getTimeOut())
        );
    }

    /**
     * 登录, 设置终端
     * @author Sober
     * @param loginId 登录id
     * @param facility 终端类型
     */

    public static void login(String loginId, String facility) {
        LOOP_AUTH_LOGIN.login(
                new TokenModel()
                        .setFacility(facility)
                        .setLoginId(loginId)
                        .setCreateTime(System.currentTimeMillis())
                        .setTimeOut(LoopAuthStrategy.getLoopAuthConfig().getTimeOut())
        );
    }

    /**
     * 登录, 设置终端
     * @author Sober
     * @param loginId 登录id
     * @param facility 终端类型
     * @param facilityName 终端设备名称
     */

    public static void login(String loginId, String facility, String facilityName) {
        LOOP_AUTH_LOGIN.login(
                new TokenModel()
                        .setFacility(facility)
                        .setFacilityName(facilityName)
                        .setLoginId(loginId)
                        .setCreateTime(System.currentTimeMillis())
                        .setTimeOut(LoopAuthStrategy.getLoopAuthConfig().getTimeOut())
        );
    }

    /**
     * 注销登录
     * @author Sober
     */
    public static void logout() {
        isLogin();
        LOOP_AUTH_LOGIN.logout();
    }

    /**
     * 注销当前会话所属loginId所有登录
     * @author Sober
     */
    public static void logoutAll() {
        isLogin();
        LOOP_AUTH_LOGIN.logoutAll();
    }

    /**
     * 获取当前请求的token模型
     * @author Sober
     * @return com.sobercoding.loopauth.model.TokenModel
     */
    public static TokenModel getTokenModel(){
        isLogin();
        return LOOP_AUTH_LOGIN.getTokenModel();
    }

    /**
     * 获取当前用户所有会话
     * @author Sober
     * @return com.sobercoding.loopauth.model.UserSession
     */
    public static UserSession getUserSession(){
        isLogin();
        return LOOP_AUTH_LOGIN.getUserSession();
    }

    /**
     * 登录校验
     * @author Sober
     */
    public static void isLogin(){
        LOOP_AUTH_LOGIN.isLogin();
    }

    /**
     * 登录续期
     * @author Sober
     */
    public static void loginRenew(){
        isLogin();
        LOOP_AUTH_LOGIN.loginRenew();
    }


    /**
     * 获取指定用户所以会话
     * @author Sober
     * @param loginId 用户id
     * @return com.sobercoding.loopauth.model.UserSession
     */
    public static UserSession getUserSessionByLoginId(String loginId){
        return LOOP_AUTH_LOGIN.getUserSessionByLoginId(loginId);
    }

    /**
     * 获取指定token的TokenModel
     * @author Sober
     * @param token token串
     * @return com.sobercoding.loopauth.model.TokenModel
     */
    public static TokenModel getTokenModelByTokenValue(String token){
        return LOOP_AUTH_LOGIN.getTokenModelByTokenValue(token);
    }

    /**
     * 强制指定token离线
     * @author Sober
     * @param tokens token值列表
     */
    public static void forcedOfflineByToken(String... tokens){
        LOOP_AUTH_LOGIN.forcedOfflineByToken(tokens);
    }

    /**
     * 强制指定用户所有会话离线
     * @author Sober
     * @param loginId 用户id
     */
    public static void forcedOfflineByLoginId(String loginId){
        LOOP_AUTH_LOGIN.forcedOfflineByLoginId(loginId);
    }


    /**
     * 角色认证
     * @author: Sober
     * @param loopAuthVerifyMode 认证方式
     * @param roles 角色列表
     */
    public static void checkByRole(LoopAuthVerifyMode loopAuthVerifyMode, String... roles) {
        isLogin();
        LOOP_AUTH_PERMISSION.checkByRole(loopAuthVerifyMode,roles);
    }

    /**
     * 角色认证
     * @author: Sober
     * @param roles 角色列表
     */
    public static void checkByRole(String... roles) {
        isLogin();
        // 默认AND
        LOOP_AUTH_PERMISSION.checkByRole(LoopAuthVerifyMode.AND,roles);
    }


    /**
     * 权限认证
     * @author: Sober
     * @param loopAuthVerifyMode 认证方式
     * @param permissions 权限代码列表
     */
    public static void checkByPermission(LoopAuthVerifyMode loopAuthVerifyMode, String... permissions) {
        isLogin();
        LOOP_AUTH_PERMISSION.checkByPermission(loopAuthVerifyMode,permissions);
    }

    /**
     * 权限认证
     * @author: Sober
     * @param permissions 权限代码列表
     */
    public static void checkByPermission(String... permissions) {
        isLogin();
        // 默认AND
        LOOP_AUTH_PERMISSION.checkByPermission(LoopAuthVerifyMode.AND,permissions);
    }

}
