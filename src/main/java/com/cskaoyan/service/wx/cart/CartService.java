package com.cskaoyan.service.wx.cart;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.spread.MallCoupon;
import com.cskaoyan.bean.admin.spread.MallGrouponRule;
import com.cskaoyan.bean.admin.userManage.Address;
import com.cskaoyan.bean.wx.cart.CartTotal;
import com.cskaoyan.bean.wx.cart.GoodInCart;
import com.cskaoyan.bean.wx.cart.ProductIsChecked;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartService {


    GoodInCart getGoodById(int goodsId);

    void insertCart(GoodInCart goodInCart);

    int goodCount(Integer userId);

    List<GoodInCart> getGoods(Integer userId);

    CartTotal getCartTotal(Integer userId);

    void setProductIsChecked(ProductIsChecked productIsChecked);

    void deleteProduct(int[] productIds);

    MallCoupon getCoupon(Integer userId, int couponId);

    MallGrouponRule getGrouponRule(int grouponRulesId);

    GoodInCart getGoodByCartId(int cartId);

    List<MallCoupon> getCouponList();

    Address getAddressById(int addressId);
}
