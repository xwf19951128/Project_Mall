package com.cskaoyan.controller.wx.login;

import com.cskaoyan.bean.wx.login.IndexOrder;
import com.cskaoyan.bean.wx.login.WxUser;
import com.cskaoyan.bean.wx.login.WxUserInfo;
import com.cskaoyan.config.TypeToken;
import com.cskaoyan.service.wx.login.WxLoginService;
import com.cskaoyan.service.wx.search.WxSearchService;
import com.cskaoyan.util.wx.CharUtil;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.cskaoyan.util.wx.UserToken;
import com.cskaoyan.util.wx.UserTokenManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/wx")
public class WxLoginController {
    @Autowired
    WxLoginService wxLoginService;

    @RequestMapping("/auth/login")
    public ResponseVo login(@RequestBody WxUser user, HttpServletRequest request){
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
            //先更新一下用户信息，比如登录时间，登录IP等
            String remoteAddr = request.getRemoteAddr();
            wxUser.setLastLoginIp(remoteAddr);
            wxUser.setLastLoginTime(new Date());
            wxLoginService.updatePassword(wxUser);

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

    //微信登录
    @RequestMapping("/auth/login_by_weixin")
    public ResponseVo weixinLogin(){
        return ResponseUtil.success();
    }

    //手机验证码，暂未实现
    @RequestMapping("auth/regCaptcha")
    public ResponseVo regCaptcha(){
        return ResponseUtil.fail(null,"小程序暂未实现验证码功能，请任意输入",701);
    }

    //注册
    @RequestMapping("/auth/register")
    public HashMap<String, Object> register(@RequestBody HashMap<String,String> paramMap){
        //接收前端的数据。暂时只有一个需求：username不能重复
        String username = paramMap.get("username");
        String password = wxLoginService.queryWxPasswordByUsername(username);
        if (password==null){
            //如果密码为空，就说明没有这个用户，可以注册
            WxUser wxUser = new WxUser();
            String mobile = paramMap.get("mobile");
            String registerPassword = paramMap.get("password");
            wxUser.setUsername(username);
            wxUser.setPassword(registerPassword);
            wxUser.setMobile(mobile);
            wxUser.setAddTime(new Date());
            wxUser.setNickname("新注册用户");
            wxLoginService.registerUser(wxUser);
            HashMap<String, Object> map = new HashMap<>();
            map.put("errmsg","注册成功");
            map.put("errno",0);
            return map;
        }else{
            HashMap<String, Object> map = new HashMap<>();
            map.put("errmsg","用户名重复，无法注册");
            map.put("errno",703);
            return map;
        }
    }

    @RequestMapping("/auth/reset")
    public HashMap<String, Object> reset(@RequestBody HashMap<String,String> paramMap){
        //首先获取手机号，判断数据库中是否有这个手机号对应的用户
        String mobile = paramMap.get("mobile");
        List<WxUser> users = wxLoginService.queryUserByMobile(mobile);
        //如果这个手机号在数据库中不存在，就无法修改
        if (users.size()==0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("errmsg","当前手机号没有用户");
            map.put("errno",703);
            return map;
        } else {
            String password = paramMap.get("password");
            WxUser wxUser = new WxUser();
            wxUser.setMobile(mobile);
            wxUser.setPassword(password);
            wxUser.setUpdateTime(new Date());
            wxLoginService.updatePassword(wxUser);
            HashMap<String, Object> map = new HashMap<>();
            map.put("errmsg","注册成功");
            map.put("errno",0);
            return map;
        }
    }
}
