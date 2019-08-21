package com.cskaoyan.mapper.system;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 14:10 2019/8/20
 */
public interface PermissionsMapper {
    List<String> selectPermissionById(int roleId);
}
