package com.cskaoyan.bean.wx.goods;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 11:41 2019/8/22
 */
public class ResponseGoodVo<T> {
    private List<T> filterCategoryList;
    private List<T> goodsList;
    private int count;

    public List<T> getFilterCategoryList() {
        return filterCategoryList;
    }

    public void setFilterCategoryList(List<T> filterCategoryList) {
        this.filterCategoryList = filterCategoryList;
    }

    public List<T> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<T> goodsList) {
        this.goodsList = goodsList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
