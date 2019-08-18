package com.cskaoyan.bean.spread;

import java.util.ArrayList;
import java.util.List;

public class ListDate<T> {
    List<T> items;
    long total;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
