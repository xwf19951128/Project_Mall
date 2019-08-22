package com.cskaoyan.bean.wx.index;

import com.cskaoyan.bean.admin.goods.Goods;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 11:30 2019/8/21
 * category表中查询id和name，商品表中查询goods，
 */
public class FloorGoodList {
    List<Goods> goodsList;
    private int id;
    private String name;

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FloorGoodList{" +
                "goodsList=" + goodsList +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
