package com.cskaoyan.bean.wx.order;

import java.util.List;

public class OrderVo {
    private double actualPrice;
    private int id;
    private boolean isGroupin;
    private String orderSn;
    private String orderStatusText;
    private List<Goods_wx> goodsList;
    private HandleOption handleOption;

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isGroupin() {
        return isGroupin;
    }

    public void setGroupin(boolean groupin) {
        isGroupin = groupin;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getOrderStatusText() {
        return orderStatusText;
    }

    public void setOrderStatusText(String orderStatusText) {
        this.orderStatusText = orderStatusText;
    }

    public List<Goods_wx> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods_wx> goodsList) {
        this.goodsList = goodsList;
    }

    public HandleOption getHandleOption() {
        return handleOption;
    }

    public void setHandleOption(HandleOption handleOption) {
        this.handleOption = handleOption;
    }
}
