package com.cskaoyan.service.admin.system.impl;

import com.cskaoyan.bean.admin.system.NewPermission;
import com.cskaoyan.bean.admin.system.PermissionL1;
import com.cskaoyan.bean.admin.system.permission.PermissionDateOne;
import com.cskaoyan.mapper.system.PermissionMapper;
import com.cskaoyan.service.admin.system.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 14:05 2019/8/20
 */
@Service
public class PermissionsServiceImpl implements PermissionsService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<PermissionL1> queryAllPermissions() {
        return permissionMapper.queryAllPermissions();
    }

    @Override
    public List<String> queryAssignPermissionByRoleId(int roleId) {
        return permissionMapper.queryAssignPermissionByRoleId(roleId);
    }

    @Override
    public List<String> queryAllPermissionName() {
        return permissionMapper.queryAllPermissionName();
    }

    @Override
    public void deletePermissionsById(int roleId) {
        permissionMapper.deletePermissionsById(roleId);
    }

    @Override
    public NewPermission queryApiByPermissionName(String name) {
        return permissionMapper.queryApiByPermissionName(name);
    }

    @Override
    public void insertPermissions(int roleId, List<NewPermission> newPermissions) {
        permissionMapper.insertPermissions(roleId,newPermissions);
    }

}
