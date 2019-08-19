package com.cskaoyan.service.mall;

import com.cskaoyan.bean.mall.order.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrderList(Short orderStatus, String orderSn, Integer userId);
}
