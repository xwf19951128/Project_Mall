package com.cskaoyan.service.wx.login;

import com.cskaoyan.bean.wx.login.WxUser;

import java.util.List;

public interface WxLoginService {
    //从user表中,根据用户名查询微信密码
    String queryWxPasswordByUsername(String username);

    //根据用户名查询User对象
    WxUser queryUserByUsername(String username);

    //查询根据状态码，查询对应的的订单数量
    int queryOrderNumByStatus(int[] status);

    void registerUser(WxUser wxUser);

    List<WxUser> queryUserByMobile(String mobile);

    void updatePassword(WxUser wxUser);

    String queryUsernameById(Integer userId);
}
