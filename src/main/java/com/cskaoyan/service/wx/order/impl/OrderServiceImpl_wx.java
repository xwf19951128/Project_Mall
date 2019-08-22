package com.cskaoyan.service.wx.order.impl;

import com.cskaoyan.bean.wx.order.OrderVo;
import com.cskaoyan.mapper.mall.OrderMapper;
import com.cskaoyan.mapper.userManage.OrderMapper_wx;
import com.cskaoyan.service.wx.order.OrderService_wx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl_wx implements OrderService_wx {

    @Autowired
    OrderMapper_wx orderMapper_wx;

    @Override
    public List<OrderVo> queryAllOrder() {
        return null;
    }

    @Override
    public List<OrderVo> queryUnpayOrder(int showType, int userId) {
        return orderMapper_wx.queryUnpayOrder(showType, userId);
    }

    @Override
    public List<OrderVo> queryUndeliveryOrder() {
        return null;
    }

    @Override
    public List<OrderVo> queryUnreceiptOrder() {
        return null;
    }

    @Override
    public List<OrderVo> queryUnjudgeOrder() {
        return null;
    }
}
