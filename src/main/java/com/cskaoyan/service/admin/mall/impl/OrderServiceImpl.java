package com.cskaoyan.service.admin.mall.impl;

import com.cskaoyan.bean.admin.mall.order.GoodsDetail;
import com.cskaoyan.bean.admin.mall.order.Order;
import com.cskaoyan.bean.admin.userManage.User;
import com.cskaoyan.mapper.mall.OrderMapper;
import com.cskaoyan.service.admin.mall.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Order> getOrderList(Short orderStatus, String orderSn, Integer userId) {
        return orderMapper.getOrderListBy(orderStatus, orderSn,userId);
    }

    @Override
    public Order getOrderById(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GoodsDetail> getOrderGoodsById(int id) {
        return orderMapper.getOrderGoodsById(id);
    }

    @Override
    public User getOrderUserById(int id) {
        return orderMapper.getOrderUserById(id);
    }
}
