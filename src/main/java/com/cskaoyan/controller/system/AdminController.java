package com.cskaoyan.controller.system;

import com.cskaoyan.bean.system.Admin;
import com.cskaoyan.bean.vo.DataBean;
import com.cskaoyan.service.system.AdminService;
import com.cskaoyan.utils.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: XiaoLei
 * @Date Created in 15:12 2019/8/16
 */
@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/admin/admin/list")
    public ResponseVo getList(int page,int limit,String username,String sort,String order){
        ResponseVo<Object> responseVo = new ResponseVo<>();
        DataBean<Admin> adminData = adminService.selectAdmins(page,limit,username,sort,order);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        responseVo.setData(adminData);
        return responseVo;
    }


}
