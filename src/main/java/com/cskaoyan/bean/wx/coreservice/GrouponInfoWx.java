package com.cskaoyan.bean.wx.coreservice;

import com.cskaoyan.bean.admin.spread.MallGroupon;
import com.cskaoyan.bean.admin.spread.MallGrouponRule;

import java.util.List;


public class GrouponInfoWx {
    private Creator creator;
    private MallGroupon groupon;
    private List<Creator> joiners;
    private int linkGrouponId;
    private List<OrderGoods> orderGoods;
    private OrderInfo orderInfo;
    private MallGrouponRule rules;

    @Override
    public String toString() {
        return "GrouponInfoWx{" +
                "creator=" + creator +
                ", groupon=" + groupon +
                ", joiners=" + joiners +
                ", linkGrouponId=" + linkGrouponId +
                ", orderGoods=" + orderGoods +
                ", orderInfo=" + orderInfo +
                ", rules=" + rules +
                '}';
    }

    public List<OrderGoods> getOrderGoods() {
        return orderGoods;
    }
    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public MallGroupon getGroupon() {
        return groupon;
    }

    public void setGroupon(MallGroupon groupon) {
        this.groupon = groupon;
    }

    public List<Creator> getJoiners() {
        return joiners;
    }

    public void setJoiners(List<Creator> joiners) {
        this.joiners = joiners;
    }

    public int getLinkGrouponId() {
        return linkGrouponId;
    }

    public void setLinkGrouponId(int linkGrouponId) {
        this.linkGrouponId = linkGrouponId;
    }

    public void setOrderGoods(List<OrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public MallGrouponRule getRules() {
        return rules;
    }

    public void setRules(MallGrouponRule rules) {
        this.rules = rules;
    }
}
