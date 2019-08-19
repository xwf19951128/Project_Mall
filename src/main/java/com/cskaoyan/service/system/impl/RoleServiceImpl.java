package com.cskaoyan.service.system.impl;

import com.cskaoyan.bean.system.Role;
import com.cskaoyan.bean.vo.DataBean;
import com.cskaoyan.bean.vo.Options;
import com.cskaoyan.mapper.system.RoleMapper;
import com.cskaoyan.service.system.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 18:50 2019/8/16
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public DataBean<Role> selectRoles(int page, int limit, String name, String sort, String order) {
        Page<Role> pageResult = PageHelper.startPage(page, limit);
        DataBean<Role> roleDataBean = new DataBean<>();
        List<Role> roles = roleMapper.selectRoles(name,sort,order);
        roleDataBean.setItems(roles);
        roleDataBean.setTotal((int) pageResult.getTotal());
        return roleDataBean;
    }

    @Override
    public List<Options> selectOptions() {
        return roleMapper.selectOptions();
    }
}