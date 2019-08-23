package com.cskaoyan.service.wx.cart.impl;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.spread.MallCoupon;
import com.cskaoyan.bean.admin.spread.MallGrouponRule;
import com.cskaoyan.bean.admin.userManage.Address;
import com.cskaoyan.bean.wx.cart.CartTotal;
import com.cskaoyan.bean.wx.cart.GoodInCart;
import com.cskaoyan.bean.wx.cart.ProductIsChecked;
import com.cskaoyan.mapper.cart.CartMapper;
import com.cskaoyan.mapper.spread.MallGrouponMapper;
import com.cskaoyan.mapper.spread.MallGrouponRuleMapper;
import com.cskaoyan.mapper.userManage.ShoppingAddressMapper;
import com.cskaoyan.service.wx.cart.CartService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    MallGrouponRuleMapper mallGrouponRuleMapper;

    @Autowired
    ShoppingAddressMapper shoppingAddressMapper;

    @Override
    public GoodInCart getGoodById(int goodsId) {
        return cartMapper.getGoodById(goodsId);
    }

    @Override
    public void setProductIsChecked(ProductIsChecked productIsChecked) {
        cartMapper.setProductIsChecked(productIsChecked.isChecked(),productIsChecked.getProductIds());
    }

    @Override
    public void insertCart(GoodInCart goodInCart) {
        cartMapper.insertCart(goodInCart);
    }

    @Override
    public int goodCount(Integer userId) {
        return cartMapper.getGoodsCount(userId);
    }

    @Override
    public List<GoodInCart> getGoods(Integer userId) {
        return cartMapper.getGoods(userId);
    }

    @Override
    public CartTotal getCartTotal(Integer userId) {
        CartTotal cartTotal = new CartTotal();
        cartTotal.setCheckedGoodsAmount(BigDecimal.valueOf(cartMapper.getCheckedGoodsAmount(userId)));
        cartTotal.setCheckGoodsCount(cartMapper.getCheckedGoodsCount(userId));
        cartTotal.setGoodsAmount(BigDecimal.valueOf(cartMapper.getGoodsAmount(userId)));
        cartTotal.setGoodsCount(cartMapper.getGoodsCount(userId));
        return cartTotal;
    }

    @Override
    public void deleteProduct(@Param("productIds") int[] productIds) {
        cartMapper.deleteProduct(productIds);
    }




    @Override
    public MallCoupon getCoupon(Integer userId, int couponId) {
        return cartMapper.getCoupon(userId, couponId);
    }

    @Override
    public MallGrouponRule getGrouponRule(int grouponRulesId) {
        return mallGrouponRuleMapper.selectByPrimaryKey(grouponRulesId);
    }

    @Override
    public GoodInCart getGoodByCartId(int cartId) {
        return cartMapper.getgoodByCartId(cartId);
    }

    @Override
    public List<MallCoupon> getCouponList() {
        return cartMapper.getCouponList();
    }

    @Override
    public Address getAddressById(int addressId) {
        return shoppingAddressMapper.queryDetailAddressById(addressId);
    }
}
