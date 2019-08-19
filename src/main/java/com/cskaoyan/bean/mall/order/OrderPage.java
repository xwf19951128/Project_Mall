package com.cskaoyan.bean.mall.order;

import java.util.List;

public class OrderPage {
    private List<Order> items;
    private int total;

    public List<Order> getItems() {
        return items;
    }

    public void setItems(List<Order> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
