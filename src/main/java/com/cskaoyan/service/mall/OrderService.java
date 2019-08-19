package com.cskaoyan.service.mall;

import com.cskaoyan.bean.goods.Goods;
import com.cskaoyan.bean.mall.order.GoodsDetail;
import com.cskaoyan.bean.mall.order.Order;
import com.cskaoyan.bean.userManage.User;

import java.util.List;

public interface OrderService {
    List<Order> getOrderList(Short orderStatus, String orderSn, Integer userId);

    Order getOrderById(int id);

    List<GoodsDetail> getOrderGoodsById(int id);

    User getOrderUserById(int id);
}
