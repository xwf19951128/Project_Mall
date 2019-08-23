package com.cskaoyan.bean.wx.order;

public class Goods_wx {
    private String goodsName;
    private int id;
    private int number;
    private String picUrl;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrls) {
        this.picUrl = picUrls;
    }
}
