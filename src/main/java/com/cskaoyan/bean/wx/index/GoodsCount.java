package com.cskaoyan.bean.wx.index;

/**
 * @Author: XiaoLei
 * @Date Created in 17:25 2019/8/22
 */
public class GoodsCount {
    private int goodsCount;

    public GoodsCount() {
    }

    public GoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    @Override
    public String toString() {
        return "GoodsCount{" +
                "goodsCount=" + goodsCount +
                '}';
    }
}
