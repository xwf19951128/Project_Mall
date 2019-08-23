package com.cskaoyan.bean.wx.cart;

import com.cskaoyan.bean.admin.goods.Goods;

import java.util.List;

public class CartIndex {

    private List<GoodInCart> cartList;

    private CartTotal cartTotal;

    public List<GoodInCart> getCartList() {
        return cartList;
    }

    public void setCartList(List<GoodInCart> cartList) {
        this.cartList = cartList;
    }

    public CartTotal getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(CartTotal cartTotal) {
        this.cartTotal = cartTotal;
    }
}
