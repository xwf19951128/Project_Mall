package com.cskaoyan.service.admin.login;

import com.cskaoyan.bean.admin.login.Admin;
import com.cskaoyan.bean.admin.login.AdminInfo;
import com.cskaoyan.bean.admin.login.DashBoard;

import java.util.List;

public interface LoginService {
    //查询用户名和密码是否正确
    public List<Admin> queryAdminByUsernameAndPassword(Admin admin);
    //查询用户权限
    AdminInfo queryAdminInfoByUsername(String username);
    //查询首页数据
    DashBoard queryDashBoard();
    //根据用户名查询密码
    List<Admin> queryPasswordByName(String name);
}
