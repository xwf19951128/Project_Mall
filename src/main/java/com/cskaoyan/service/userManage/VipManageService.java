package com.cskaoyan.service.userManage;

import com.cskaoyan.bean.userManage.User;

import java.util.List;

public interface VipManageService {
    // 查询所有用户信息
    List<User> queryAllUser();
}
