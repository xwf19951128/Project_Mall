package com.cskaoyan.mapper.cart;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.spread.MallCoupon;
import com.cskaoyan.bean.wx.cart.GoodInCart;
import com.cskaoyan.bean.wx.cart.ProductIsChecked;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CartMapper {
    int getUserIdByUsername(String username);

    GoodInCart getGoodById(int goodsId);

    void insertCart(@Param("good") GoodInCart goodInCart);

    int goodCount();

    double getCheckedGoodsAmount(Integer userId);

    int getCheckedGoodsCount(Integer userId);

    double getGoodsAmount(Integer userId);

    int getGoodsCount(Integer userId);

    List<GoodInCart> getGoods(int userId);

    void setProductIsChecked(int checked, @Param("productIds") int[] productIds);

    void deleteProduct(int[] productIds);

    MallCoupon getCoupon(@Param("userId") Integer userId, @Param("couponId") int couponId);

    GoodInCart getGoodByCartId(int cartId);

    List<MallCoupon> getCouponList();

    void updateGood(@Param("good") GoodInCart goodInCart);
}
