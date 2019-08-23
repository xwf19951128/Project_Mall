package com.cskaoyan.controller.wx.cart;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.mall.order.Order;
import com.cskaoyan.bean.admin.spread.MallCoupon;
import com.cskaoyan.bean.admin.spread.MallGrouponRule;
import com.cskaoyan.bean.admin.userManage.Address;
import com.cskaoyan.bean.wx.cart.*;
import com.cskaoyan.bean.wx.coreservice.Cart;
import com.cskaoyan.service.admin.goods.GoodsService;
import com.cskaoyan.service.wx.cart.CartService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.cskaoyan.util.wx.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("wx/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("add")
    public ResponseVo addCart(HttpServletRequest request, @RequestBody GoodInCart goodInCart) {

        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        GoodInCart good = setGood(userId, goodInCart);

        cartService.insertCart(good);
        return ResponseUtil.success(cartService.goodCount(userId));
    }


    @RequestMapping("fastadd")
    public ResponseVo fastAdd(HttpServletRequest request, @RequestBody GoodInCart goodInCart) {
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        GoodInCart good = setGood(userId, goodInCart);
        cartService.insertCart(good);
        /*要改*/
        return ResponseUtil.success(good.getId());  // 返回快速插入中的购物车id
    }

    @RequestMapping("index")
    public ResponseVo index(HttpServletRequest request) {
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        CartIndex cartIndex = getIndex(userId);
        return ResponseUtil.success(cartIndex);
    }

    @RequestMapping("checked")
    public ResponseVo checked(HttpServletRequest request, @RequestBody ProductIsChecked productIsChecked) {
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        cartService.setProductIsChecked(productIsChecked);
        CartIndex cartIndex = getIndex(userId);
        return ResponseUtil.success(cartIndex);
    }

    @RequestMapping("delete")
    public ResponseVo delete(HttpServletRequest request, @RequestBody int[] productIds) {
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        cartService.deleteProduct(productIds);
        CartIndex cartIndex = getIndex(userId);
        return ResponseUtil.success(cartIndex);
    }

    @RequestMapping("goodscount")
    public ResponseVo goodsCount(HttpServletRequest request) {
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        int count = cartService.goodCount(userId);
        return ResponseUtil.success(count);
    }

    @RequestMapping("checkout")
    public ResponseVo checkout(HttpServletRequest request, int cartId, int addressId, int couponId, int grouponRulesId) {
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);

        /*获取地址*/
        Address address = cartService.getAddressById(addressId);

        BigDecimal grouponPrice = new BigDecimal("0");  // 团购优惠总额


        if (grouponRulesId != 0) {
            MallGrouponRule grouponRule = cartService.getGrouponRule(grouponRulesId);
            if (grouponRule.getExpireTime().compareTo(new Date()) > 0) {  // 优惠券未到期
                grouponPrice.add(grouponRule.getDiscount());
            }
        }

        Order2Checkout order2Checkout = new Order2Checkout();
        order2Checkout.setAddressId(addressId);
        order2Checkout.setCheckedAddress(address);
        order2Checkout.setCouponId(couponId);
        order2Checkout.setGrouponRulesId(grouponRulesId);
        order2Checkout.setGrouponPrice(grouponPrice);


        if (cartId != 0) {  // fastAdd 跳转
            List<GoodInCart> goods = new ArrayList<>();
            GoodInCart good = cartService.getGoodByCartId(cartId);
            goods.add(good);
            setOrderByGoods(goods, couponId, grouponPrice, userId, order2Checkout);
        } else {
            List<GoodInCart> goods = cartService.getGoods(userId);
            setOrderByGoods(goods, couponId, grouponPrice, userId, order2Checkout);
        }
        request.getSession().setAttribute("order2checkout",order2Checkout);
        return ResponseUtil.success(order2Checkout);
    }




    /*获取购物车主页信息*/
    private CartIndex getIndex(Integer userId) {
        CartIndex cartIndex = new CartIndex();
        List<GoodInCart> cartList = cartService.getGoods(userId);
        CartTotal cartTotal = cartService.getCartTotal(userId);
        cartIndex.setCartList(cartList);
        cartIndex.setCartTotal(cartTotal);
        return cartIndex;
    }


    /*设置商商品的完整属性*/
    private GoodInCart setGood(Integer userId, GoodInCart goodInCart) {
        int number = goodInCart.getNumber();
        goodInCart = cartService.getGoodById(goodInCart.getGoodsId());
        goodInCart.setUserId(userId);
        goodInCart.setAddTime(new Date());
        goodInCart.setUpdateTime(new Date());
        goodInCart.setNumber(number);
        return goodInCart;
    }

    private void setOrderByGoods(List<GoodInCart> goods, int couponId, BigDecimal grouponPrice, Integer userId, Order2Checkout order2Checkout) {
        int availableCouponLength = 0;
        BigDecimal actualPrice = new BigDecimal("0");  // 货品总价
        BigDecimal freightPrice = new BigDecimal("0");  // 运费总额
        BigDecimal goodsTotalPrice = new BigDecimal("0");  // 总额
        BigDecimal couponPrice = new BigDecimal("0");  // 优惠劵总额
        for (GoodInCart good : goods) {  // 计算商品总价
            if (good.isChecked()) {
                goodsTotalPrice.add(good.getPrice()) ;
            }
            actualPrice = goodsTotalPrice;
        }


        if (goodsTotalPrice.compareTo(new BigDecimal(88)) < 0) {
            freightPrice.add(new BigDecimal(10));  // 加10元运费
            actualPrice.add(freightPrice);
        }

        if (couponId != 0) {
            MallCoupon coupon = cartService.getCoupon(userId, couponId);
            if (goodsTotalPrice.compareTo( coupon.getMin()) >= 0) {
                couponPrice.add(coupon.getDiscount());
            }
            actualPrice.subtract(couponPrice);
            List<MallCoupon> coupons = cartService.getCouponList();
            for (MallCoupon mallCoupon : coupons) {
                if (goodsTotalPrice.compareTo(mallCoupon.getMin()) > 0) {
                    availableCouponLength++;
                }
            }
        }
        order2Checkout.setActualPrice(actualPrice);
        order2Checkout.setCouponPrice(couponPrice);
        order2Checkout.setFreightPrice(freightPrice);
        order2Checkout.setOrderTotalPrice(actualPrice);
        order2Checkout.setAvailableCouponLength(availableCouponLength);
        order2Checkout.setCheckedGoodsList(goods);
        order2Checkout.setGoodsTotalPrice(goodsTotalPrice);

    }

}
