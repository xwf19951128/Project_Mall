package com.cskaoyan.mapper.system;

import com.cskaoyan.bean.admin.system.NewPermission;
import com.cskaoyan.bean.admin.system.Permission;
import com.cskaoyan.bean.admin.system.PermissionExample;
import com.cskaoyan.bean.admin.system.PermissionL1;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    long countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    //查询所有的权限
    List<PermissionL1> queryAllPermissions();

    //查询当前用户的权限
    List<String> queryAssignPermissionByRoleId(@Param("roleId") int roleId);

    //当用户权限为*时，手动查询所有的权限名字
    List<String> queryAllPermissionName();

    //删除当前用户的所有权限
    void deletePermissionsById(@Param("roleId") int roleId);

    //更新用户权限name到name字段中
    void updatePermissions(@Param("roleId") int roleId, @Param("permissions") List<String> permissions);

    //根据权限的name查询api
    NewPermission queryApiByPermissionName(@Param("name") String name);

    //插入权限的api到permission字段中
    void insertPermissions(@Param("roleId") int roleId, @Param("newPermissions") List<NewPermission> newPermissions);
}