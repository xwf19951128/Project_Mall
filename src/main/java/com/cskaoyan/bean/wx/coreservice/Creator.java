package com.cskaoyan.bean.wx.coreservice;

public class Creator {
    private String nickname;
    private String avatar;

    @Override
    public String toString() {
        return "Creator{" +
                "nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
