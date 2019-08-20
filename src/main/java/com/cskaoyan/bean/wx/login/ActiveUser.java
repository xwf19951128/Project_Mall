package com.cskaoyan.bean.wx.login;

public class ActiveUser {
    int userId;
    String username;

    public ActiveUser() {
    }

    public ActiveUser(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ActiveUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
