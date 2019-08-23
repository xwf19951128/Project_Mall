package com.cskaoyan.service.wx.index;

import com.cskaoyan.bean.admin.goods.Goods;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 17:11 2019/8/22
 */
public interface GoodListService {

    List<Goods> selectAllCountGoods();
}
