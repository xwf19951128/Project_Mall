package com.cskaoyan.service.admin.system.impl;

import com.cskaoyan.bean.admin.system.Admin;
import com.cskaoyan.bean.admin.vo.DataBean;
import com.cskaoyan.mapper.system.AdminMapper;
import com.cskaoyan.service.admin.system.AdminService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 15:27 2019/8/16
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public DataBean<Admin> selectAdmins(int page, int limit, String username, String sort, String order) {
        Page<Object> pageResult = PageHelper.startPage(page, limit);
        List<Admin> admins = adminMapper.selectAdmins(username,sort,order);
        DataBean<Admin> adminDataBean = new DataBean<>();
        adminDataBean.setTotal((int) pageResult.getTotal());
        adminDataBean.setItems(admins);
        return adminDataBean;
    }

    @Override
    public int insertAdmin(Admin admin) {
        return adminMapper.insertAdmin(admin);
    }

    @Override
    public int updateAdmin(Admin admin) {
       return adminMapper.updateAdmin(admin);
        //下面是逆向工程的写法。
        // return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public int deleteAdmin(Integer id) {
        return adminMapper.deleteByPrimaryKey(id);
    }
}
