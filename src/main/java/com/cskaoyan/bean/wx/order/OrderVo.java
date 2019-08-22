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
}
