package com.cskaoyan.mapper.userManage;

import com.cskaoyan.bean.userManage.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VipManageMapper {
    // 查询所有用户信息
    List<User> queryAllUser();
}
