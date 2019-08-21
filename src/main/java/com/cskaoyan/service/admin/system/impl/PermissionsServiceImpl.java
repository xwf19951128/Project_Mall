package com.cskaoyan.service.admin.system.impl;

import com.cskaoyan.bean.admin.system.permission.PermissionDateOne;
import com.cskaoyan.mapper.system.PermissionsMapper;
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
    PermissionsMapper permissionsMapper;

    @Override
    public PermissionDateOne selectPermission(int roleId) {
        PermissionDateOne permissionDateOne = new PermissionDateOne();
        //根据id去permission表查找该用户的权限。
        List<String> assignedPermissions = permissionsMapper.selectPermissionById(roleId);
        //
//        List<PermissionDateTwo> permissionDateTwos =
        return null;
    }
}
