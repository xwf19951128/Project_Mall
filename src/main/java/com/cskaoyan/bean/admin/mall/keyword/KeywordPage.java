package com.cskaoyan.bean.admin.mall.keyword;

import java.util.List;

public class KeywordPage {

    private List<Keyword> items;

    private int total;

    public List<Keyword> getItems() {
        return items;
    }

    public void setItems(List<Keyword> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
