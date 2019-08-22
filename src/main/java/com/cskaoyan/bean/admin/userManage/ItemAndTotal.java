package com.cskaoyan.bean.admin.userManage;

import java.util.List;

public class ItemAndTotal<T> {
    private List<T> items;
    private int total;

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
}
