package com.cskaoyan.service.admin.system;

import com.cskaoyan.bean.admin.system.Role;
import com.cskaoyan.bean.admin.vo.DataBean;
import com.cskaoyan.bean.admin.vo.Options;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 18:50 2019/8/16
 */
public interface RoleService {
    DataBean<Role> selectRoles(int page, int limit, String name, String sort, String order);

    List<Options> selectOptions();

    int insertRole(Role role);

    int updateRole(Role role);

    int deleteRole(Role role);
    
}
