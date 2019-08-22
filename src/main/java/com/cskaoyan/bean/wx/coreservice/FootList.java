package com.cskaoyan.bean.wx.coreservice;

import java.util.List;

public class FootList<T> {
    List<T> footprintList;
    long totalPages;

    public List<T> getFootprintList() {
        return footprintList;
    }

    public void setFootprintList(List<T> footprintList) {
        this.footprintList = footprintList;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }
}
