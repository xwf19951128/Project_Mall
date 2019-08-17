package com.cskaoyan.service.login;

import com.cskaoyan.bean.login.Admin;
import com.cskaoyan.bean.login.AdminInfo;
import com.cskaoyan.bean.login.DashBoard;

import java.util.List;

public interface LoginService {
    //查询用户名和密码是否正确
    public List<Admin> queryAdminByUsernameAndPassword(Admin admin);
    //查询用户权限
    AdminInfo queryAdminInfoByUsername(String username);
    //查询首页数据
    DashBoard queryDashBoard();
}
