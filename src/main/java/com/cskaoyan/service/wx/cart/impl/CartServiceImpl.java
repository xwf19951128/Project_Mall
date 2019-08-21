package com.cskaoyan.service.wx.cart.impl;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.wx.cart.CartTotal;
import com.cskaoyan.bean.wx.cart.GoodInCart;
import com.cskaoyan.mapper.cart.CartMapper;
import com.cskaoyan.service.wx.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper cartMapper;

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

    @Override
    public List<Goods> getGoods(Integer userId) {
        return cartMapper.getGoods(userId);
    }

    @Override
    public CartTotal getCartTotal(Integer userId) {
        CartTotal cartTotal = new CartTotal();
        cartTotal.setCheckedGoodsAmount(cartMapper.getCheckedGoodsAmount(userId));
        cartTotal.setCheckGoodsCount(cartMapper.getCheckedGoodsCount(userId));
        cartTotal.setGoodsAmount(cartMapper.getGoodsAmount(userId));
        cartTotal.setGoodsCount(cartMapper.getGoodsCount(userId));
        return cartTotal;
    }
}
