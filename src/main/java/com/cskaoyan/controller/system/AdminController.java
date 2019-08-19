package com.cskaoyan.controller.system;

import com.cskaoyan.annotation.SystemLog;
import com.cskaoyan.bean.system.Admin;
import com.cskaoyan.bean.vo.DataBean;
import com.cskaoyan.service.system.AdminService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: XiaoLei
 * @Date Created in 15:12 2019/8/16
 */
@RestController
public class AdminController {

    @Autowired
    AdminService adminService;


    @SystemLog(desc = "查询管理员")
    @RequestMapping("/admin/admin/list")
    public ResponseVo getList(int page,int limit,String username,String sort,String order){
        ResponseVo<Object> responseVo = new ResponseVo<>();
        DataBean<Admin> adminData = adminService.selectAdmins(page,limit,username,sort,order);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        responseVo.setData(adminData);
        return responseVo;
    }

    //增
    @SystemLog(desc = "增加管理员")
    @RequestMapping("/admin/admin/create")
    public ResponseVo addAdmin(@RequestBody Admin admin){
        ResponseVo responseVo = new ResponseVo();
        admin.setAddTime(new Date());
        admin.setUpdateTime(new Date());
        admin.setDeleted(false);
        int insert = adminService.insertAdmin(admin);
        if(insert!=0){
            responseVo.setErrno(0);
            responseVo.setData(admin);
            responseVo.setErrmsg("成功");
        }
        return responseVo;
    }
    //改
    @SystemLog(desc = "编辑管理员")
    @RequestMapping("/admin/admin/update")
    public ResponseVo updateAdmin(@RequestBody Admin admin){
        ResponseVo responseVo = new ResponseVo();
        admin.setAddTime(new Date());
        admin.setUpdateTime(new Date());
        admin.setDeleted(false);
        int update=adminService.updateAdmin(admin);
        System.out.println(update);
        if(update!=0){
            responseVo.setErrmsg("成功");
            responseVo.setErrno(0);
            responseVo.setData(admin);
        }
        return responseVo;
    }
    //删
    @SystemLog(desc = "删除管理员")
    @RequestMapping("/admin/admin/delete")
    public ResponseVo deleteAdmin(@RequestBody Admin admin){
        ResponseVo responseVo = new ResponseVo();
        int delete=adminService.deleteAdmin(admin.getId());
        if(delete!=0){
            responseVo.setErrno(0);
            responseVo.setData(null);
            responseVo.setErrmsg("成功");
        }
        return responseVo;
    }


}
