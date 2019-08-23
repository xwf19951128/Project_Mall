package com.cskaoyan.bean.wx.index.vo;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 18:31 2019/8/23
 */
public class WxCommentVo2<T> {
    private String addTime;
    private String content;
    List<T> picList;
    UserInfo userInfo;


    public WxCommentVo2(String addTime, String content, List<T> picList, UserInfo userInfo) {
        this.addTime = addTime;
        this.content = content;
        this.picList = picList;
        this.userInfo = userInfo;
    }

    public WxCommentVo2() {
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<T> getPicList() {
        return picList;
    }

    public void setPicList(List<T> picList) {
        this.picList = picList;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
