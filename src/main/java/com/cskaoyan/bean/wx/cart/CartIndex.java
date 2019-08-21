package com.cskaoyan.bean.wx.cart;

import com.cskaoyan.bean.admin.goods.Goods;

import java.util.List;

public class CartIndex {

    private List<Goods> cartList;

    private CartTotal cartTotal;

    public List<Goods> getCartList() {
        return cartList;
    }

    public void setCartList(List<Goods> cartList) {
        this.cartList = cartList;
    }

    public CartTotal getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(CartTotal cartTotal) {
        this.cartTotal = cartTotal;
    }
}
