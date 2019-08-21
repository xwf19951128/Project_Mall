package com.cskaoyan.bean.wx.cart;

public class CartTotal {

    private double checkedGoodsAmount;

    private int checkGoodsCount;

    private double goodsAmount;

    private int goodsCount;

    public double getCheckedGoodsAmount() {
        return checkedGoodsAmount;
    }

    public void setCheckedGoodsAmount(double checkedGoodsAmount) {
        this.checkedGoodsAmount = checkedGoodsAmount;
    }

    public int getCheckGoodsCount() {
        return checkGoodsCount;
    }

    public void setCheckGoodsCount(int checkGoodsCount) {
        this.checkGoodsCount = checkGoodsCount;
    }

    public double getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(double goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }
}
