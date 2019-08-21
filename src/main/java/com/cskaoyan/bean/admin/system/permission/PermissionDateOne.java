package com.cskaoyan.bean.admin.system.permission;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 11:32 2019/8/20
 */
public class PermissionDateOne {
    private List<String> assignedPermissions;
    private List<PermissionDateTwo> systemPermissions;

    public List<String> getAssignedPermissions() {
        return assignedPermissions;
    }

    public void setAssignedPermissions(List<String> assignedPermissions) {
        this.assignedPermissions = assignedPermissions;
    }

    public List<PermissionDateTwo> getSystemPermissions() {
        return systemPermissions;
    }

    public void setSystemPermissions(List<PermissionDateTwo> systemPermissions) {
        this.systemPermissions = systemPermissions;
    }

    @Override
    public String toString() {
        return "PermissionDateOne{" +
                "assignedPermissions=" + assignedPermissions +
                ", systemPermissions=" + systemPermissions +
                '}';
    }
}
