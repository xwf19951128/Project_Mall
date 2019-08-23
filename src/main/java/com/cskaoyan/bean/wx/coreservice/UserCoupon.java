package com.cskaoyan.bean.wx.coreservice;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserCoupon {
    private Integer id;
    private double discount;
    private Integer userId;
    private String name;
    private Integer couponId;
    private String tag;
    private Short status;
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date usedTime;
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    private String desc;
    private Integer orderId;
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private double min;
    private Boolean deleted;
    private double limit;

    @Override
    public String toString() {
        return "UserCoupon{" +
                "id=" + id +
                ", discount=" + discount +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", couponId=" + couponId +
                ", tag='" + tag + '\'' +
                ", status=" + status +
                ", usedTime=" + usedTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", desc='" + desc + '\'' +
                ", orderId=" + orderId +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", min=" + min +
                ", deleted=" + deleted +
                ", limit=" + limit +
                '}';
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Date getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}