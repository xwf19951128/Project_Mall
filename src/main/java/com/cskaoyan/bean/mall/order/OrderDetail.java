package com.cskaoyan.bean.mall.order;

import com.cskaoyan.bean.goods.Goods;
import com.cskaoyan.bean.userManage.User;

import java.util.List;

public class OrderDetail {
    private Order order;
    private List<GoodsDetail> orderGoods;
    private User user;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<GoodsDetail> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<GoodsDetail> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
