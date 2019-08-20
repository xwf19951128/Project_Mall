package com.cskaoyan.mapper.userManage;

import com.cskaoyan.bean.admin.userManage.User;

import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface VipManageMapper {
    // 查询所有用户信息
    List<User> queryAllUser();

    // 通过用户名和手机号查询用户
    List<User> queryUserByUsernameAndMobile(@Param("username") String username, @Param("mobile") String mobile);

    // 通过用户名查询用户
    List<User> queryUserByUsername(@Param("username") String username);

    // 通过手机号查询用户
    List<User> queryUserByMobile(@Param("mobile") String mobile);

}
