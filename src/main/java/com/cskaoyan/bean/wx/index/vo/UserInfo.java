package com.cskaoyan.bean.wx.index.vo;

/**
 * @Author: XiaoLei
 * @Date Created in 18:33 2019/8/23
 */
public class UserInfo {
    private String avatarurl;
    private String nickName;

    public UserInfo() {
    }

    public UserInfo(String avatarurl, String nickName) {
        this.avatarurl = avatarurl;
        this.nickName = nickName;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
