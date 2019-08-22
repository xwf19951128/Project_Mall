package com.cskaoyan.mapper.userManage;

import com.cskaoyan.bean.wx.order.Goods_wx;
import com.cskaoyan.bean.wx.order.OrderInfo;
import com.cskaoyan.bean.wx.order.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper_wx {

    List<Goods_wx> queryGoodsListByOrderId(@Param("orderId") Integer id);

    OrderInfo getOrderByOrderId(@Param("orderId") int orderId);

    int getOrderStatusByOrderId(@Param("orderId") int orderId);
}
