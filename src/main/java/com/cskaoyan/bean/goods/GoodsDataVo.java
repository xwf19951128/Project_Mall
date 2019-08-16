package com.cskaoyan.bean.goods;

import java.util.List;

public class GoodsDataVo<T> {

    private long total;

    private List<T> items;

    public GoodsDataVo() {
    }

    public GoodsDataVo(long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public long getTotal() {
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
