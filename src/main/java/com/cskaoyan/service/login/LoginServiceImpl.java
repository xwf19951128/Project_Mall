package com.cskaoyan.service.login;

import com.cskaoyan.bean.login.Admin;
import com.cskaoyan.bean.login.AdminExample;
import com.cskaoyan.bean.login.AdminInfo;
import com.cskaoyan.bean.login.DashBoard;
import com.cskaoyan.mapper.login.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginServiceImpl implements LoginService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<Admin> queryAdminByUsernameAndPassword(Admin admin) {
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andUsernameEqualTo(admin.getUsername()).andPasswordEqualTo(admin.getPassword());
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        return admins;
    }

    @Override
    public AdminInfo queryAdminInfoByUsername(String username) {
        return adminMapper.queryAdminInfoByUsername(username);
    }

    @Override
    public DashBoard queryDashBoard() {
        return adminMapper.queryDashBoard();
    }
}
