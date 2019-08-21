package com.cskaoyan.mapper.cart;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.wx.cart.GoodInCart;

import java.util.List;

public interface CartMapper {
    int getUserIdByUsername(String username);

    GoodInCart getGoodById(int goodsId);

    void insertCart( GoodInCart goodInCart);

    int goodCount();

    double getCheckedGoodsAmount(Integer userId);

    int getCheckedGoodsCount(Integer userId);

    double getGoodsAmount(Integer userId);

    int getGoodsCount(Integer userId);

    List<Goods> getGoods(Integer userId);
}
