package com.cskaoyan.bean.wx.login;

import java.time.LocalDateTime;
import java.util.HashMap;

public class WxUserInfo {
    Object token;
    LocalDateTime tokenExpire;
    HashMap<String,String> userInfo;


    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public LocalDateTime getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(LocalDateTime tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    public HashMap<String, String> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(HashMap<String, String> userInfo) {
        this.userInfo = userInfo;
    }
}
