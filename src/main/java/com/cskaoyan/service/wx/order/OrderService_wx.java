package com.cskaoyan.service.wx.order;

import com.cskaoyan.bean.wx.order.OrderVo;

import java.util.List;

public interface OrderService_wx {

    List<OrderVo> queryAllOrder();

    List<OrderVo> queryUnpayOrder();

    List<OrderVo> queryUndeliveryOrder();

    List<OrderVo> queryUnreceiptOrder();

    List<OrderVo> queryUnjudgeOrder();

}
