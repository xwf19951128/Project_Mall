package com.cskaoyan.mapper.userManage;

import com.cskaoyan.bean.userManage.User;
<<<<<<< .merge_file_a12364
import org.apache.ibatis.annotations.Param;
=======
>>>>>>> .merge_file_a14228
import org.springframework.stereotype.Component;

import java.util.List;

<<<<<<< .merge_file_a12364
public interface VipManageMapper {
    // 查询所有用户信息
    List<User> queryAllUser();

    // 通过用户名和手机号查询用户
    List<User> queryUserByUsernameAndMobile(@Param("username") String username, @Param("mobile") String mobile);

    // 通过用户名查询用户
    List<User> queryUserByUsername(@Param("username") String username);

    // 通过手机号查询用户
    List<User> queryUserByMobile(@Param("mobile") String mobile);
=======
@Component
public interface VipManageMapper {
    // 查询所有用户信息
    List<User> queryAllUser();
>>>>>>> .merge_file_a14228
}
