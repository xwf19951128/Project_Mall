package com.cskaoyan.bean.wx.order;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class orderGoods {
    private int id;
    private int number;
    private int orderId;
    private int goodsId;
    private int productId;
    private double price;
    private String goodsName;
    private int comment;
    private String goodsSn;
    private String picUrl;
    private String[] specification;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private boolean deleted;
}
