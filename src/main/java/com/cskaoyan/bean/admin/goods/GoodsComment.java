package com.cskaoyan.bean.admin.goods;

import java.util.Date;
import java.util.List;

public class GoodsComment {
    private Integer id;

    private Integer valueId;

    private Byte type;

    private String content;

    private Integer userId;

    private Boolean hasPicture;

    private List<String> picUrls;

    private Short star;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValueId() {
        return valueId;
    }

    public void setValueId(Integer valueId) {
        this.valueId = valueId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getHasPicture() {
        return hasPicture;
    }

    public void setHasPicture(Boolean hasPicture) {
        this.hasPicture = hasPicture;
    }

    public List<String> getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
    }

    public Short getStar() {
        return star;
    }

    public void setStar(Short star) {
        this.star = star;
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

    @Override
    public String toString() {
        return "GoodsComment{" +
                "id=" + id +
                ", valueId=" + valueId +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", hasPicture=" + hasPicture +
                ", picUrls=" + picUrls +
                ", star=" + star +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                '}';
    }
}