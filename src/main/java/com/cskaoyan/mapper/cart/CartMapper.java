package com.cskaoyan.mapper.cart;

import com.cskaoyan.bean.wx.cart.GoodInCart;

public interface CartMapper {
    int getUserIdByUsername(String username);

    GoodInCart getGoodById(int goodsId);

    void insertCart( GoodInCart goodInCart);

    int goodCount();

}
