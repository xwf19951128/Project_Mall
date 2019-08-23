package com.cskaoyan.bean.wx.index.vo;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 19:27 2019/8/22
 */
public class WxCommentDate<T> {
    private int count;
    private int currentPage;
    private List<T> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
