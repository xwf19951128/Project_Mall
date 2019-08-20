package com.cskaoyan.service.wx.login;

import com.cskaoyan.bean.wx.login.WxUser;
import com.cskaoyan.bean.wx.login.WxUserExample;
import com.cskaoyan.mapper.login.LoginMapper;
import com.cskaoyan.mapper.login.WxUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WxLoginServiceImpl implements WxLoginService {
    @Autowired
    WxUserMapper wxUserMapper;
    @Autowired
    LoginMapper loginMapper;

    @Override
    public String queryWxPasswordByUsername(String username) {
        return loginMapper.queryWxPasswordByUsername(username);
    }

    @Override
    public WxUser queryUserByUsername(String username) {
        WxUserExample wxUserExample = new WxUserExample();
        WxUserExample.Criteria criteria = wxUserExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        return wxUserMapper.selectByExample(wxUserExample).get(0);
    }
}
