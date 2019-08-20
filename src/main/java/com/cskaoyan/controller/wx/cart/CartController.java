package com.cskaoyan.controller.wx.cart;

import com.cskaoyan.bean.wx.GoodInCart;
import com.cskaoyan.service.admin.goods.GoodsService;
import com.cskaoyan.service.wx.CartService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("wx/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("add")
    public ResponseVo addCart(@RequestBody GoodInCart goodInCart) {
        int number = goodInCart.getNumber();
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        int userId = cartService.getUserIdByUsername(username);
        goodInCart = cartService.getGoodById(goodInCart.getGoodsId());
        goodInCart.setUserId(userId);
        goodInCart.setAddTime(new Date());
        goodInCart.setUpdateTime(new Date());
        goodInCart.setNumber(number);
        cartService.insertCart(goodInCart);
        return ResponseUtil.success(cartService.goodCount());
    }


}
