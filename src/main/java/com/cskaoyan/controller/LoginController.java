package com.cskaoyan.controller;

import com.cskaoyan.bean.vo.Data;
import com.cskaoyan.bean.vo.ResponseVo2;
import com.cskaoyan.bean.vo.ResponseVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @Author: XiaoLei
 * @Date Created in 22:30 2019/8/15
 */
@Controller
public class LoginController {

    @RequestMapping("/admin/auth/login")
    @ResponseBody
    public ResponseVo Login(){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setData("c4f33a09-62f4-458e-9f8e-d234bbb8d728");
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }
    @RequestMapping("/admin/auth/info")
    @ResponseBody
    public ResponseVo2 Info(){
        Data data = new Data();
        ResponseVo2 responseVo2 = new ResponseVo2();
        String[] roles = {"超级管理员"};
        String[] perms = {"*"};
        data.setRoles(roles);
        data.setName("admin123");
        data.setPerms(perms);
        responseVo2.setData(data);
        responseVo2.setErrmsg("成功");
        responseVo2.setErrno(0);
        return responseVo2;

    }
}
