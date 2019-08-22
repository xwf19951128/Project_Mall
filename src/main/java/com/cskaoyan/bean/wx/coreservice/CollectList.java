package com.cskaoyan.bean.wx.coreservice;

import java.util.List;

public class CollectList<T> {
    List<T> collectList;
    long totalPages;



    public List<T> getCollectList() {
        return collectList;
    }

    public void setCollectList(List<T> collectList) {
        this.collectList = collectList;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }


}
