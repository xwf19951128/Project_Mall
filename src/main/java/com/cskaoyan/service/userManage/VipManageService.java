package com.cskaoyan.service.userManage;

import com.cskaoyan.bean.userManage.User;

import java.util.List;

public interface VipManageService {
    // 查询所有用户信息
    List<User> queryAllUser();
<<<<<<< .merge_file_a14228

    // 通过用户名和手机号查询用户
    List<User> queryUserByUsernameAndMobile(String username, String mobile);

    // 通过用户名查询用户
    List<User> queryUserByUsername(String username);

    // 通过手机号查询用户
    List<User> queryUserByMobile(String mobile);
=======
>>>>>>> .merge_file_a12160
}
