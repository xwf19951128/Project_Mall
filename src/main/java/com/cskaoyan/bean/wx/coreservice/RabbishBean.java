package com.cskaoyan.bean.wx.coreservice;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.spread.MallGroupon;
import com.cskaoyan.bean.admin.spread.MallGrouponRule;
import com.cskaoyan.bean.wx.order.HandleOption;

import java.util.List;

public class RabbishBean {
    private double actualPrice;
    private String creator;
    private List<Goods> goodsList;
    private MallGroupon groupon;
    private int id;
    private int creatorId;
    private boolean CreatorOwner;
    private int joinerCount;
    private int orderId;
    private int orderSn;
    private String orderStatusText;
    private int orderStatus;
    private int userId;
    private HandleOption handleOption;
private int comments;

    public HandleOption getHandleOption() {
        return handleOption;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public void setHandleOption(HandleOption handleOption) {
        this.handleOption = handleOption;
    }

    public int getJoinerCount() {
        return joinerCount;
    }

    public void setJoinerCount(int joinerCount) {
        this.joinerCount = joinerCount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    private MallGrouponRule rules;

    @Override
    public String toString() {
        return "RabbishBean{" +
                "actualPrice=" + actualPrice +
                ", creator='" + creator + '\'' +
                ", goodsList=" + goodsList +
                ", groupon=" + groupon +
                ", id=" + id +
                ", creatorId=" + creatorId +
                ", CreatorOwner=" + CreatorOwner +
                ", joinCount=" + joinerCount +
                ", orderId=" + orderId +
                ", orderSn=" + orderSn +
                ", orderStatusText='" + orderStatusText + '\'' +
                ", orderStatus=" + orderStatus +
                ", rules=" + rules +
                ", handleOption='" + handleOption + '\'' +
                '}';
    }

    public boolean isCreatorOwner() {
        return CreatorOwner;
    }

    public void setCreatorOwner(boolean creatorOwner) {
        CreatorOwner = creatorOwner;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public MallGroupon getGroupon() {
        return groupon;
    }

    public void setGroupon(MallGroupon groupon) {
        this.groupon = groupon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(int orderSn) {
        this.orderSn = orderSn;
    }

    public String getOrderStatusText() {
        return orderStatusText;
    }

    public void setOrderStatusText(String orderStatusText) {
        this.orderStatusText = orderStatusText;
    }

    public MallGrouponRule getRules() {
        return rules;
    }

    public void setRules(MallGrouponRule rules) {
        this.rules = rules;
    }


}
