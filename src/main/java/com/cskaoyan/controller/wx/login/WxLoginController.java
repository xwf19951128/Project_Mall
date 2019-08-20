package com.cskaoyan.controller.wx.login;

import com.cskaoyan.bean.wx.login.WxUser;
import com.cskaoyan.bean.wx.login.WxUserInfo;
import com.cskaoyan.config.TypeToken;
import com.cskaoyan.service.wx.login.WxLoginService;
import com.cskaoyan.util.CharUtil;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@RequestMapping("/wx/auth")
public class WxLoginController {
    @Autowired
    WxLoginService wxLoginService;

    @RequestMapping("/login")
    public ResponseVo login(@RequestBody WxUser user){
        String username = user.getUsername();
        String password = user.getPassword();
        TypeToken typeToken = new TypeToken(username, password, "wx");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(typeToken);
        }catch (AuthenticationException e){
            return ResponseUtil.fail(null,"账号密码不对",700);
        }
        //如果没有发生异常，说明登录认证成功，开始封装要返回的数据
        WxUserInfo wxUserInfo = new WxUserInfo();
        //去数据库中查询当前username对应的User，获取其他属性
        WxUser wxUser = wxLoginService.queryUserByUsername(username);
        wxUserInfo.setTokenExpire(LocalDateTime.now().plusDays(1));
        String randomString = CharUtil.getRandomString(32);
        wxUserInfo.setToken(randomString);
        HashMap<String, String> map = new HashMap<>();
        map.put("avatarUrl",wxUser.getAvatar());
        map.put("nickName",wxUser.getNickname());
        wxUserInfo.setUserInfo(map);
        System.out.println(subject.getPrincipal());
        return ResponseUtil.success(wxUserInfo);
    }

    @RequestMapping("/logout")
    public ResponseVo logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResponseUtil.success();
    }
}
