package com.cskaoyan.service.system.impl;

import com.cskaoyan.bean.system.Admin;
import com.cskaoyan.bean.vo.DataBean;
import com.cskaoyan.mapper.system.AdminMapper;
import com.cskaoyan.service.system.AdminService;
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
}
