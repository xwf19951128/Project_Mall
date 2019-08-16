package com.cskaoyan.bean.spread;

import java.util.ArrayList;
import java.util.List;

public class ListDate<T> {
    ArrayList<T> items;
    long total;

    public ArrayList<T> getItems() {
        return items;
    }

    public void setItems(ArrayList<T> items) {
        this.items = items;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
