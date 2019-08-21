package com.cskaoyan.mapper.system;

import com.cskaoyan.bean.admin.system.Role;
import com.cskaoyan.bean.admin.system.RoleExample;
import java.util.List;

import com.cskaoyan.bean.admin.vo.Options;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKey(Role record);

    List<Role> selectRoles(@Param("name") String name, @Param("sort") String sort, @Param("order") String order);

    List<Options> selectOptions();

    int insertRole(Role role);

    int updateRole(Role role);
}