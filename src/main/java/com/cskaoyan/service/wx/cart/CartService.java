package com.cskaoyan.service.wx.cart;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.wx.cart.CartTotal;
import com.cskaoyan.bean.wx.cart.GoodInCart;

import java.util.List;

public interface CartService {


    GoodInCart getGoodById(int goodsId);

    void insertCart(GoodInCart goodInCart);

    int goodCount();

    List<Goods> getGoods(Integer userId);

    CartTotal getCartTotal(Integer userId);
}
