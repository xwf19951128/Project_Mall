package com.cskaoyan.service.admin.mall;

import com.cskaoyan.bean.admin.mall.order.GoodsDetail;
import com.cskaoyan.bean.admin.mall.order.Order;
import com.cskaoyan.bean.admin.userManage.User;

import java.util.List;

public interface OrderService {
    List<Order> getOrderList(Short orderStatus, String orderSn, Integer userId);

    Order getOrderById(int id);

    List<GoodsDetail> getOrderGoodsById(int id);

    User getOrderUserById(int id);
}
