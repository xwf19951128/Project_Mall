package com.cskaoyan.bean.wx.cart;

import java.math.BigDecimal;

public class CartTotal {

    private BigDecimal checkedGoodsAmount;

    private int checkGoodsCount;

    private BigDecimal goodsAmount;

    private int goodsCount;

    public BigDecimal getCheckedGoodsAmount() {
        return checkedGoodsAmount;
    }

    public void setCheckedGoodsAmount(BigDecimal checkedGoodsAmount) {
        this.checkedGoodsAmount = checkedGoodsAmount;
    }

    public int getCheckGoodsCount() {
        return checkGoodsCount;
    }

    public void setCheckGoodsCount(int checkGoodsCount) {
        this.checkGoodsCount = checkGoodsCount;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }
}
