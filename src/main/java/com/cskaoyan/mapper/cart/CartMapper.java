package com.cskaoyan.mapper.cart;

import com.cskaoyan.bean.wx.GoodInCart;
import org.apache.ibatis.annotations.Param;

public interface CartMapper {
    int getUserIdByUsername(String username);

    GoodInCart getGoodById(int goodsId);

    void insertCart(@Param() GoodInCart goodInCart);

    int goodCount();

}
