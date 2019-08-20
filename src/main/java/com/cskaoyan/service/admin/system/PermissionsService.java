package com.cskaoyan.service.admin.system;

import com.cskaoyan.bean.admin.system.permission.PermissionDateOne;

/**
 * @Author: XiaoLei
 * @Date Created in 14:04 2019/8/20
 */
public interface PermissionsService {
    PermissionDateOne selectPermission(int roleId);
}
