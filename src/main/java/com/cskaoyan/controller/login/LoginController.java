package com.cskaoyan.controller.login;

import com.cskaoyan.bean.vo.Data;

import com.cskaoyan.util.ResponseVo;

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
    public ResponseVo<String> Login(){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setData("c4f33a09-62f4-458e-9f8e-d234bbb8d728");
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }

    @RequestMapping("/admin/auth/info")
    @ResponseBody
    public ResponseVo<Data> Info(){
        Data data = new Data();
        ResponseVo responseVo = new ResponseVo();
        String[] roles = {"超级管理员"};
        String[] perms = {"*"};
        data.setRoles(roles);
        data.setName("admin123");
        data.setPerms(perms);
        data.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        responseVo.setData(data);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }
}
