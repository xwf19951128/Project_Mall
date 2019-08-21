package com.cskaoyan.mapper.system;

import com.cskaoyan.bean.admin.system.Admin;
import com.cskaoyan.bean.admin.system.AdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> selectAdmins(@Param("username") String username,@Param("sort") String sort,@Param("order") String order);

    //插入admin
    int insertAdmin(Admin admin);

    int updateAdmin(Admin admin);

    String getPassWordByName(String username);

    List<String> getPermissionsByName(String usernmae);
}