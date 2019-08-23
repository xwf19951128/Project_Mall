package com.cskaoyan.service.wx.login;

import com.cskaoyan.bean.admin.mall.order.OrderExample;
import com.cskaoyan.bean.wx.login.WxUser;
import com.cskaoyan.bean.wx.login.WxUserExample;
import com.cskaoyan.mapper.login.LoginMapper;
import com.cskaoyan.mapper.login.WxUserMapper;
import com.cskaoyan.mapper.mall.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class WxLoginServiceImpl implements WxLoginService {
    @Autowired
    WxUserMapper wxUserMapper;
    @Autowired
    LoginMapper loginMapper;
    @Autowired
    OrderMapper orderMapper;

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

    @Override
    public int queryOrderNumByStatus(int[] status) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        ArrayList<Object> list = new ArrayList<>();
        for (int i : status) {
            list.add(i);
        }
        criteria.andOrderStatusIn(list);
        return (int) orderMapper.countByExample(example);
    }

    @Override
    public void registerUser(WxUser wxUser) {
        wxUserMapper.insertSelective(wxUser);
    }

    @Override
    public List<WxUser> queryUserByMobile(String mobile) {
        WxUserExample wxUserExample = new WxUserExample();
        WxUserExample.Criteria criteria = wxUserExample.createCriteria();
        criteria.andMobileEqualTo(mobile);
        return wxUserMapper.selectByExample(wxUserExample);
    }

    @Override
    public void updatePassword(WxUser wxUser) {
        WxUserExample userExample = new WxUserExample();
        WxUserExample.Criteria criteria = userExample.createCriteria();
        criteria.andMobileEqualTo(wxUser.getMobile());
        wxUserMapper.updateByExampleSelective(wxUser,userExample);
    }

    @Override
    public String queryUsernameById(Integer userId) {
        return loginMapper.queryWxUsernameById(userId);
    }
}
