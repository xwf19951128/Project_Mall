package com.cskaoyan.bean.spread;

import java.util.Arrays;

public class GrouponInfo {
    private Goods goods;
    private MallGroupon groupon;
    private MallGrouponRule rules;
    private String[] subGroupons;

    @Override
    public String toString() {
        return "GrouponInfo{" +
                "goods=" + goods +
                ", groupon=" + groupon +
                ", rules=" + rules +
                ", subGroupons=" + Arrays.toString(subGroupons) +
                '}';
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public MallGroupon getGroupon() {
        return groupon;
    }

    public void setGroupon(MallGroupon groupon) {
        this.groupon = groupon;
    }

    public MallGrouponRule getRules() {
        return rules;
    }

    public void setRules(MallGrouponRule rules) {
        this.rules = rules;
    }

    public String[] getSubGroupons() {
        return subGroupons;
    }

    public void setSubGroupons(String[] subGroupons) {
        this.subGroupons = subGroupons;
    }
}
