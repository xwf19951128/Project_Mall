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
    //根据用户名查询密码
    List<Admin> queryPasswordByName(String name);

    //修改密码时，同时修改当前用户的密码以及更新时间update_time
    int updatePasswordAndTime(Admin admin);

    //登录时时候，修改最后一次登录时间以及IP地址
    int updateIPAndLastTime(Admin admin);
}
