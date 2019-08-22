package com.cskaoyan.service.wx.order;

import com.cskaoyan.bean.wx.order.OrderVo;

import java.util.List;

public interface OrderService_wx {

    // 全部订单
    List<OrderVo> queryAllOrder();

    // 待付款
    List<OrderVo> queryUnpayOrder(int showType, int userId);

    // 代发货
    List<OrderVo> queryUndeliveryOrder();

    // 待收货
    List<OrderVo> queryUnreceiptOrder();

    // 未评价
    List<OrderVo> queryUnjudgeOrder();

}
