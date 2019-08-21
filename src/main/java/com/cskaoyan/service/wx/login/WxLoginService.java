package com.cskaoyan.service.wx.login;

import com.cskaoyan.bean.wx.login.WxUser;

public interface WxLoginService {
    //从user表中,根据用户名查询微信密码
    String queryWxPasswordByUsername(String username);

    //根据用户名查询User对象
    WxUser queryUserByUsername(String username);
}
