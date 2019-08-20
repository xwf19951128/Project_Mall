package com.cskaoyan.bean.admin.userManage;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class VipCollect {

    private int deleted;
    private int id;
    private int type;
    private int userId;
    private int valueId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getValueId() {
        return valueId;
    }

    public void setValueId(int valueId) {
        this.valueId = valueId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "VipCollect{" +
                "deleted=" + deleted +
                ", id=" + id +
                ", type=" + type +
                ", userId=" + userId +
                ", valueId=" + valueId +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
