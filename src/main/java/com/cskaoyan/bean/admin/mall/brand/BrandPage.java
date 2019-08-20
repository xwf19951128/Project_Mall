package com.cskaoyan.bean.admin.mall.brand;

import java.util.List;

public class BrandPage {
    private List<Brand> items;
    private int total;

    public List<Brand> getItems() {
        return items;
    }

    public void setItems(List<Brand> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
