package com.cskaoyan.util.wx;

import java.time.LocalDateTime;

/**
 * userToken是持久化保存在服务器端的数据，用于记录用户信息
 * */
public class UserToken {
    private Integer userId;
    private String token;
    private String sessionKey;
    private LocalDateTime expireTime;
    private LocalDateTime updateTime;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
