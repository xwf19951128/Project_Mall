package com.cskaoyan.bean.wx.coreservice;

import java.util.List;

public class ListDateWX<T> {
    List<T> data;
    long count;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
