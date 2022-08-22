package com.sobercoding.loopauth.model;

import com.sobercoding.loopauth.LoopAuthStrategy;
import com.sobercoding.loopauth.util.LoopAuthUtil;

import java.io.Serializable;
import java.util.Objects;

/**
 * @program: LoopAuth
 * @author: Sober
 * @Description: token模型
 * @create: 2022/07/22 20:06
 */
public class TokenModel implements Serializable,Comparable<TokenModel>  {

    private static final long serialVersionUID = 1L;

    /**
     * token值
     */
    private String value;

    /**
     * 删除标记
     */
    private boolean removeFlag;

    /**
     * 用户id
     */
    private String loginId;

    /**
     * 设备值
     */
    private String facility;

    /**
     * 创建时间
     */
    private long createTime;

    /**
     * token有效期(单位毫秒)
     */
    private long timeOut;

    public boolean isRemoveFlag() {
        return removeFlag;
    }

    public TokenModel setRemoveFlag(boolean removeFlag) {
        this.removeFlag = removeFlag;
        return this;
    }

    public String getLoginId() {
        return loginId;
    }

    public TokenModel setLoginId(String loginId) {
        this.loginId = loginId;
        return this;
    }

    public TokenModel setValue(String value) {
        this.value = value;
        return this;
    }

    public TokenModel setFacility(String facility) {
        this.facility = facility;
        return this;
    }

    public TokenModel setCreateTime(long createTime) {
        this.createTime = createTime;
        return this;
    }

    public TokenModel setTimeOut(long timeOut) {
        this.timeOut = timeOut;
        return this;
    }

    public String getValue() {
        return value;
    }

    public String getFacility() {
        return facility;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getTimeOut() {
        return timeOut;
    }

    /**
     * @Method: getTokenModel
     * @Author: Sober
     * @Version: 0.0.1
     * @Description: 对内存的直接操作
     * 获取内存中当前token的TokenModel
     * @Return: void
     * @Exception:
     * @Date: 2022/8/11 0:42
     */
    public TokenModel getTokenModel(){
        return (TokenModel) LoopAuthStrategy.getLoopAuthDao()
                .get(LoopAuthStrategy.getLoopAuthConfig().getTokenPersistencePrefix() +
                        ":" +
                        value);
    }

    /**
     * @Method: setTokenModel
     * @Author: Sober
     * @Version: 0.0.1
     * @Description: 对内存的直接操作
     * 获取内存中当前token的TokenModel
     * @param expirationTime 到期时间
     * @Return: void
     * @Exception:
     * @Date: 2022/8/11 0:42
     */
    public void setTokenModel(long expirationTime){
        LoopAuthStrategy.getLoopAuthDao()
                .set(
                        LoopAuthStrategy.getLoopAuthConfig().getTokenPersistencePrefix() + ":" + value,
                        this,
                        expirationTime
                );
    }

    /**
     * @Method: remove
     * @Author: Sober
     * @Version: 0.0.1
     * @Description: 对内存的直接操作，删除当前token会话
     * @Return:
     * @Exception:
     * @Date: 2022/8/11 15:41
     */
    public void remove(){
        LoopAuthStrategy.getLoopAuthDao()
                .remove(LoopAuthStrategy.getLoopAuthConfig().getTokenPersistencePrefix() +
                        ":" +
                        value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        TokenModel tokenModel = (TokenModel) obj;

        return Objects.equals(this.value, tokenModel.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    @Override
    public int compareTo(TokenModel obj) {
        return Integer.compare(this.hashCode() - obj.hashCode(), 0);
    }
    @Override
    public String toString() {
        return "TokenModel{" +
                "value='" + value + '\'' +
                ", facility='" + facility + '\'' +
                ", createTime=" + createTime +
                ", timeOut=" + timeOut +
                '}';
    }
}
