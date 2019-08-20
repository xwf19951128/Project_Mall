package com.cskaoyan.service.wx;

import com.cskaoyan.bean.wx.GoodInCart;

public interface CartService {

    int getUserIdByUsername(String username);

    GoodInCart getGoodById(int goodsId);

    void insertCart(GoodInCart goodInCart);

    int goodCount();

}
