package com.cskaoyan.controller.xwf;

import com.cskaoyan.bean.login.LoginTest;
import com.cskaoyan.bean.login.ResponseVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class HelloController {
    @RequestMapping("/auth/login")
    @ResponseBody
    public ResponseVo login(){
        ResponseVo<Object> result = new ResponseVo<>();
        UUID uuid = UUID.randomUUID();
        result.setData(uuid);
        result.setErrmsg("成功");
        result.setErrno(0);
        return result;
    }

    @RequestMapping("/auth/info")
    @ResponseBody
    public ResponseVo info(){
        ResponseVo<Object> result = new ResponseVo<>();
        LoginTest loginTest = new LoginTest();
        loginTest.setAvatar("http://www.hao123.com");
        loginTest.setName("admin123");
        loginTest.setPerms(new String[]{"*"});
        loginTest.setRoles(new String[]{"超级管理员"});
        result.setErrmsg("成功");
        result.setErrno(0);
        result.setData(loginTest);
        return result;
    }
}
