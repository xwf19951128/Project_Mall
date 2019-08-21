package com.cskaoyan.service.admin.login;

import com.cskaoyan.bean.admin.login.Admin;
import com.cskaoyan.bean.admin.login.AdminExample;
import com.cskaoyan.bean.admin.login.AdminInfo;
import com.cskaoyan.bean.admin.login.DashBoard;
import com.cskaoyan.bean.wx.login.WxUser;
import com.cskaoyan.bean.wx.login.WxUserExample;
import com.cskaoyan.mapper.login.LoginMapper;
import com.cskaoyan.mapper.login.WxUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;
    @Autowired
    WxUserMapper wxUserMapper;

    @Override
    public List<Admin> queryAdminByUsernameAndPassword(Admin admin) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andUsernameEqualTo(admin.getUsername()).andPasswordEqualTo(admin.getPassword());
        List<Admin> admins = loginMapper.selectByExample(adminExample);
        return admins;
    }

    @Override
    public AdminInfo queryAdminInfoByUsername(String username) {
        return loginMapper.queryAdminInfoByUsername(username);
    }

    @Override
    public DashBoard queryDashBoard() {
        return loginMapper.queryDashBoard();
    }

    @Override
    public List<Admin> queryPasswordByName(String name) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andUsernameEqualTo(name);
        List<Admin> adminList = loginMapper.selectByExample(adminExample);
        return adminList;
    }

    @Override
    public int updatePasswordAndTime(Admin admin) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andUsernameEqualTo(admin.getUsername());
        return loginMapper.updateByExampleSelective(admin,adminExample);
    }

    @Override
    public int updateIPAndLastTime(Admin admin) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andUsernameEqualTo(admin.getUsername());
        return loginMapper.updateByExampleSelective(admin,adminExample);
    }


}
