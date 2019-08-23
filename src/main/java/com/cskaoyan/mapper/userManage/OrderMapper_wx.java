package com.cskaoyan.mapper.userManage;

import com.cskaoyan.bean.admin.mall.order.GoodsDetail;
import com.cskaoyan.bean.wx.order.Goods_wx;
import com.cskaoyan.bean.wx.order.OrderInfo;
import com.cskaoyan.bean.wx.order.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper_wx {

    List<Goods_wx> queryGoodsListByOrderId(@Param("orderId") Integer id);

    OrderInfo getOrderByOrderId(@Param("orderId") int orderId);

    int getOrderStatusByOrderId(@Param("orderId") int orderId);

    void deleteOrderByOrderId(@Param("orderId") int orderId);

    void deleteOrder_GoodsByOrderId(@Param("orderId") int orderId);

    void confirmOrder(@Param("orderStatus") int orderStatus, @Param("orderId") int orderId);

    void cancelOrderByOrderId(@Param("orderStatus") int orderStatus, @Param("orderId") int orderId);

    void refundByOrderId(@Param("orderStatus") int orderStatus, @Param("orderId") int orderId);

    GoodsDetail queryOrderGoods(@Param("orderId") int orderId, @Param("goodsId") int goodsId);
}
