package com.cskaoyan.controller.wx.login;

import com.cskaoyan.bean.wx.login.IndexOrder;
import com.cskaoyan.bean.wx.login.WxUser;
import com.cskaoyan.bean.wx.login.WxUserInfo;
import com.cskaoyan.config.TypeToken;
import com.cskaoyan.service.wx.login.WxLoginService;
import com.cskaoyan.util.wx.CharUtil;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.cskaoyan.util.wx.UserToken;
import com.cskaoyan.util.wx.UserTokenManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@RequestMapping("/wx")
public class WxLoginController {
    @Autowired
    WxLoginService wxLoginService;

    @RequestMapping("/auth/login")
    public ResponseVo login(@RequestBody WxUser user){
        String username = user.getUsername();
        String password = user.getPassword();
            //如果没有发生异常，说明登录认证成功，开始封装要返回的数据
            WxUserInfo wxUserInfo = new WxUserInfo();
            //去数据库中查询当前username对应的User，获取其他属性
        String dbPassword = wxLoginService.queryWxPasswordByUsername(username);
        if (!password.equals(dbPassword)){
                return ResponseUtil.fail(null,"账号或密码不对",700);
            }else {
            //如果正确的话，就查询出该用户的所有信息
            WxUser wxUser = wxLoginService.queryUserByUsername(username);
            wxUserInfo.setTokenExpire(LocalDateTime.now().plusDays(1));
                HashMap<String, String> map = new HashMap<>();
                map.put("avatarUrl",wxUser.getAvatar());
                map.put("nickName",wxUser.getNickname());
                wxUserInfo.setUserInfo(map);
                // token
                UserToken userToken = UserTokenManager.generateToken(wxUser.getId());
                wxUserInfo.setToken(userToken.getToken());
                return ResponseUtil.success(wxUserInfo);
            }
    }

    @RequestMapping("/user/index")
    //这个方法是用来读取四种订单的数量并显示出来
    public ResponseVo index(){
        IndexOrder order = new IndexOrder();
        int num1 = wxLoginService.queryOrderNumByStatus(new int[]{101});
        order.setUnpaid(num1);
        int num2 = wxLoginService.queryOrderNumByStatus(new int[]{201});
        order.setUnship(num2);
        int num3 = wxLoginService.queryOrderNumByStatus(new int[]{301});
        order.setUnrecv(num3);
        int num4 = wxLoginService.queryOrderNumByStatus(new int[]{401, 402});
        order.setUncomment(num4);
        HashMap<String, Object> map = new HashMap<>();
        map.put("order",order);
        return ResponseUtil.success(map);
    }

    @RequestMapping("/auth/logout")
    public ResponseVo logout() {
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
        return ResponseUtil.success();
    }
}
