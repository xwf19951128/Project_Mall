package com.cskaoyan.service.userManage.impl;

import com.cskaoyan.bean.userManage.User;
import com.cskaoyan.mapper.userManage.VipManageMapper;
import com.cskaoyan.service.userManage.VipManageService;
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


}
