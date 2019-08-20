package com.cskaoyan.service.wx.impl;

import com.cskaoyan.bean.wx.GoodInCart;
import com.cskaoyan.mapper.cart.CartMapper;
import com.cskaoyan.service.wx.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper cartMapper;

    @Override
    public int getUserIdByUsername(String username) {
        return cartMapper.getUserIdByUsername(username);
    }

    @Override
    public GoodInCart getGoodById(int goodsId) {
        return cartMapper.getGoodById(goodsId);
    }

    @Override
    public void insertCart(GoodInCart goodInCart) {
        cartMapper.insertCart(goodInCart);
    }

    @Override
    public int goodCount() {
        return cartMapper.goodCount();
    }
}
