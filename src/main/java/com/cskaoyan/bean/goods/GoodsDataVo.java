package com.cskaoyan.bean.goods;

import java.util.List;

public class GoodsDataVo<T> {

    private int total;

    private List<T> items;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "GoodsDataVo{" +
                "total=" + total +
                ", items=" + items +
                '}';
    }
}
