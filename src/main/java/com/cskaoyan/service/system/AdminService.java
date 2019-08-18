package com.cskaoyan.service.system;

import com.cskaoyan.bean.system.Admin;
import com.cskaoyan.bean.vo.DataBean;

/**
 * @Author: XiaoLei
 * @Date Created in 15:28 2019/8/16
 */
public interface AdminService {
    DataBean<Admin> selectAdmins(int page, int limit, String username, String sort, String order);

    //增
    int insertAdmin(Admin admin);

    //改
    int updateAdmin(Admin admin);

    int deleteAdmin(Integer id);
}
