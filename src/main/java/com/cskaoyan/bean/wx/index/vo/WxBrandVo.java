package com.cskaoyan.bean.wx.index.vo;

import com.cskaoyan.bean.admin.mall.brand.Brand;

/**
 * @Author: XiaoLei
 * @Date Created in 23:10 2019/8/22
 */
public class WxBrandVo {
    private Brand brand;

    public WxBrandVo() {
    }

    public WxBrandVo(Brand brand) {
        this.brand = brand;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "WxBrandVo{" +
                "brand=" + brand +
                '}';
    }
}
