package com.cskaoyan.bean.wx.index;

import com.cskaoyan.bean.admin.goods.Goods;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 11:29 2019/8/21
 *团购价：应该为实际价格-折扣价格
 * 去折扣规定表查询，价格和数量
 */
public class GroupOnList {
    private double grouponPrice;
    private int grouponMember;
    private Goods goods;

    public double getGrouponPrice() {
        return grouponPrice;
    }

    public void setGrouponPrice(double grouponPrice) {
        this.grouponPrice = grouponPrice;
    }

    public int getGrouponMember() {
        return grouponMember;
    }

    public void setGrouponMember(int grouponMember) {
        this.grouponMember = grouponMember;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "GroupOnList{" +
                "grouponPrice=" + grouponPrice +
                ", grouponMember=" + grouponMember +
                ", goods=" + goods +
                '}';
    }
}

