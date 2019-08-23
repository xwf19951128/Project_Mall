package com.cskaoyan.service.wx.order;

import com.cskaoyan.bean.wx.order.OrderVo;

import java.util.HashMap;
import java.util.List;

public interface OrderService_wx {

    // 全部订单
    List<OrderVo> queryAllOrder(int userId);

    // 待付款
    List<OrderVo> queryUnpayOrder(int showType, int userId);

    // 代发货
    List<OrderVo> queryUndeliveryOrder(int showType, int userId);

    // 待收货
    List<OrderVo> queryUnreceiptOrder(int showType, int userId);

    // 未评价
    List<OrderVo> queryUnjudgeOrder(int showType, int userId);

    // 查订单详情
    HashMap<String, Object> queryOrderDetail(int orderId);
}
