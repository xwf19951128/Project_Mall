package com.cskaoyan.service.admin.system;

import com.cskaoyan.bean.admin.system.NewPermission;
import com.cskaoyan.bean.admin.system.PermissionL1;
import com.cskaoyan.bean.admin.system.permission.PermissionDateOne;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 14:04 2019/8/20
 */
public interface PermissionsService {

    //查询所有的权限，分为三层；为了方便展示出来让用户选择
    List<PermissionL1> queryAllPermissions();

    //查询当前用户所拥有的权限。如果查询结果为*，就去查询所有的权限
    List<String> queryAssignPermissionByRoleId(int roleId);

    //当用户权限为*时，手动查询所有的权限名字
    List<String> queryAllPermissionName();

    void deletePermissionsById(int roleId);



    NewPermission queryApiByPermissionName(String name);

    void insertPermissions(int roleId, List<NewPermission> newPermissions);
}
