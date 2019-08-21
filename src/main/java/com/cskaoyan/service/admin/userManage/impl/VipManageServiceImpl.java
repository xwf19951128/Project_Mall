package com.cskaoyan.service.admin.userManage.impl;

import com.cskaoyan.bean.admin.userManage.User;
import com.cskaoyan.mapper.userManage.VipManageMapper;
import com.cskaoyan.service.admin.userManage.VipManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VipManageServiceImpl implements VipManageService {

    // 从容器中取出mapper接口
    @Autowired
    VipManageMapper vipManageMapper;

    // 查询所有用户信息
    @Override
    public List<User> queryAllUser() {
        return vipManageMapper.queryAllUser();
    }


    // 通过用户名和手机号查询用户
    @Override
    public List<User> queryUserByUsernameAndMobile(String username, String mobile) {
        return vipManageMapper.queryUserByUsernameAndMobile(username, mobile);
    }

    // 通过用户名查询用户
    @Override
    public List<User> queryUserByUsername(String username) {
        return vipManageMapper.queryUserByUsername(username);
    }

    // 通过手机号查询用户
    @Override
    public List<User> queryUserByMobile(String mobile) {
        return vipManageMapper.queryUserByMobile(mobile);
    }

}
