package com.cskaoyan.controller.wx.cart;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.wx.cart.CartIndex;
import com.cskaoyan.bean.wx.cart.CartTotal;
import com.cskaoyan.bean.wx.cart.GoodInCart;
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
        int number = goodInCart.getNumber();
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        goodInCart = cartService.getGoodById(goodInCart.getGoodsId());
        goodInCart.setUserId(userId);
        goodInCart.setAddTime(new Date());
        goodInCart.setUpdateTime(new Date());
        goodInCart.setNumber(number);
        cartService.insertCart(goodInCart);
        return ResponseUtil.success(cartService.goodCount());
    }


    @RequestMapping("index")
    public ResponseVo index(HttpServletRequest request) {
        String tokenKey = request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tokenKey);
        CartIndex cartIndex = new CartIndex();
        List<Goods> cartList = cartService.getGoods(userId);
        CartTotal cartTotal = cartService.getCartTotal(userId);
        cartIndex.setCartList(cartList);
        cartIndex.setCartTotal(cartTotal);
        return ResponseUtil.success(cartIndex);
    }
}
