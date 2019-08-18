package com.cskaoyan.bean.mall.issue;

import java.util.List;

public class IssuePage {

    private List<Issue> items;

    private int total;

    public List<Issue> getItems() {
        return items;
    }

    public void setItems(List<Issue> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
