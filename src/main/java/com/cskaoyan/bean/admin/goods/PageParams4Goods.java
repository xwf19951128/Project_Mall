package com.cskaoyan.bean.admin.goods;

public class PageParams4Goods {

    private int page;

    private int limit;

    private String sort;

    private String order;


    public PageParams4Goods() {
    }

    public PageParams4Goods(int page, int limit, String sort, String order) {
        this.page = page;
        this.limit = limit;
        this.sort = sort;
        this.order = order;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return "PageGoods{" +
                "page=" + page +
                ", limit=" + limit +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                '}';
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
