package com.cskaoyan.bean.vo;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 15:50 2019/8/16
 * 该bean表示了Date的一种形式，含一个集合和一个int数字，最后放入date
 */
public class DataBean<T> {
    private List<T> items;
    private int total;

    public DataBean() {
    }

    public DataBean(List<T> items, int total) {
        this.items = items;
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "items=" + items +
                ", total=" + total +
                '}';
    }
}
