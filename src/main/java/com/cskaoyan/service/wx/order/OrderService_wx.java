package com.cskaoyan.service.wx.order;

import com.cskaoyan.bean.admin.mall.order.GoodsDetail;
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

    // 取消订单
    void cancelOrderByOrderId(int orderId);

    // 确认收货
    void confirmOrder(int orderId);

    // 删除订单
    void deleteOrderByOrderId(int orderId);

    // 再次购买
    /*List<Goods> reBuyById(int id);*/

    // 申请退款
    void refundByOrderId(int orderId);

    // 评论跳转页面
    GoodsDetail queryOrderGoods(int orderId, int goodsId);

    // 提交评论
    void insertComment(HashMap<String, Object> map, int userId);



}
