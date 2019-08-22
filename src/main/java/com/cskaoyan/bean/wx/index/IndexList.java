package com.cskaoyan.bean.wx.index;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.mall.brand.Brand;
import com.cskaoyan.bean.admin.mall.category.Category;
import com.cskaoyan.bean.admin.spread.MallAD;
import com.cskaoyan.bean.admin.spread.MallCoupon;
import com.cskaoyan.bean.admin.spread.MallTopic;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 11:05 2019/8/21
 */
public class IndexList {
    //广告横幅
    List<MallAD> banner;
    //品牌制造商直供 ，对应brand
    List<Brand> brandList;
    //渠道，也就是分类，category
    List<Category> channel;
    //优惠券列表，对应MallCoupon
    List<MallCoupon> couponList;
    //最底部的商品列表。带着很多商品，新建一个javabean
    List<FloorGoodList> floorGoodsList;
    //团购专区，最低价，有number和价格，去rule查询
    List<GroupOnList> groupOnList;
    //热门商品展示
    List<Goods> hotGoodsList;
    //最新商品展示
    List<Goods> newGoodsList;
    //专题精选
    List<MallTopic> topicList;

    public List<MallAD> getBanner() {
        return banner;
    }

    public void setBanner(List<MallAD> banner) {
        this.banner = banner;
    }

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }

    public List<Category> getChannel() {
        return channel;
    }

    public void setChannel(List<Category> channel) {
        this.channel = channel;
    }

    public List<MallCoupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<MallCoupon> couponList) {
        this.couponList = couponList;
    }

    public List<FloorGoodList> getFloorGoodsList() {
        return floorGoodsList;
    }

    public void setFloorGoodsList(List<FloorGoodList> floorGoodsList) {
        this.floorGoodsList = floorGoodsList;
    }

    public List<GroupOnList> getGroupOnList() {
        return groupOnList;
    }

    public void setGroupOnList(List<GroupOnList> groupOnList) {
        this.groupOnList = groupOnList;
    }

    public List<Goods> getHotGoodsList() {
        return hotGoodsList;
    }

    public void setHotGoodsList(List<Goods> hotGoodsList) {
        this.hotGoodsList = hotGoodsList;
    }

    public List<Goods> getNewGoodsList() {
        return newGoodsList;
    }

    public void setNewGoodsList(List<Goods> newGoodsList) {
        this.newGoodsList = newGoodsList;
    }

    public List<MallTopic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<MallTopic> topicList) {
        this.topicList = topicList;
    }
}
