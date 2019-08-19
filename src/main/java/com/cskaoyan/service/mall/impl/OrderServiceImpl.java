package com.cskaoyan.service.mall.impl;

import com.cskaoyan.bean.mall.order.Order;
import com.cskaoyan.mapper.mall.OrderMapper;
import com.cskaoyan.service.mall.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Order> getOrderList(Short orderStatus, String orderSn, Integer userId) {
        List<Order> items = null;
        if (orderStatus == null && orderSn == null && userId == null) {
            items = orderMapper.getOrderList();
        }
        return items;
    }
}
