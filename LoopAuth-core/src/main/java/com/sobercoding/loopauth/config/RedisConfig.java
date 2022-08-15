package com.sobercoding.loopauth.config;

import java.io.Serializable;

/**
 * <p>
 * Description: Redis 配置类
 * </p>
 *
 * @author: Weny
 * @date: 2022/8/13
 * @see: com.sobercoding.loopauth.config
 * @version: v1.0.0
 */
public class RedisConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Redis服务器地址
     */
    private String host = "127.0.0.1";

    /**
     * Redis 密码，没有设置可以为空
     */
    private String password = "";

    /**
     * Redis端口号， 默认是6379
     */
    private int port = 6379;

    /**
     * Redis 默认链接的数据库
     */
    private int databaseNo = 0;

    /**
     * 超时时间
     */
    private int timeout = 2000;

    /**
     * 最大连接数
     */
    private int maxTotal = 16;

    /**
     * 最大空闲连接数
     */
    private int maxIdle = 8;

    /**
     * 最小空闲连接数
     */
    private int minIdle = 4;

    /**
     * 需要连接池
     */
    private boolean needPool = true;

    public RedisConfig setHost(String host) {
        this.host = host;
        return this;
    }

    public RedisConfig setPassword(String password) {
        this.password = password;
        return this;
    }

    public RedisConfig setPort(int port) {
        this.port = port;
        return this;
    }

    public RedisConfig setDatabaseNo(int databaseNo) {
        this.databaseNo = databaseNo;
        return this;
    }

    public RedisConfig setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public RedisConfig setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
        return this;
    }

    public RedisConfig setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
        return this;
    }

    public RedisConfig setMinIdle(int minIdle) {
        this.minIdle = minIdle;
        return this;
    }

    public RedisConfig setNeedPool(boolean needPool) {
        this.needPool = needPool;
        return this;
    }

    public String getHost() {
        return host;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public int getDatabaseNo() {
        return databaseNo;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public boolean isNeedPool() {
        return needPool;
    }

    @Override
    public String toString() {
        return "RedisConfig{" +
                "host='" + host + '\'' +
                ", password='" + password + '\'' +
                ", port=" + port +
                ", databaseNo=" + databaseNo +
                ", timeout=" + timeout +
                ", maxTotal=" + maxTotal +
                ", maxIdle=" + maxIdle +
                ", minIdle=" + minIdle +
                ", needPool=" + needPool +
                '}';
    }
}
