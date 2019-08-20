package com.cskaoyan.controller.admin.login;

import com.cskaoyan.bean.admin.login.Admin;
import com.cskaoyan.bean.admin.login.AdminInfo;
import com.cskaoyan.bean.admin.login.DashBoard;
import com.cskaoyan.service.admin.login.LoginService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping("/auth/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }

    @RequestMapping("/auth/login")
    @ResponseBody
    public ResponseVo login(@RequestBody Admin admin, HttpSession session){
        String username = admin.getUsername();
        String password =admin.getPassword();
        ResponseVo responseVo =null;
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            Serializable id = subject.getSession().getId();
            responseVo = ResponseUtil.success(id);
        } catch (AuthenticationException e) {
            responseVo = ResponseUtil.fail(null, "用户名或密码不正确", 605);
        }
        return responseVo;
    }

    @RequestMapping("/auth/info")
    @ResponseBody
    public ResponseVo info(){
        Subject subject = SecurityUtils.getSubject();
        String principal = (String) subject.getPrincipal();
        AdminInfo adminInfo = loginService.queryAdminInfoByUsername(principal);
        return ResponseUtil.success(adminInfo);
    }

    @RequestMapping("/dashboard")
    @ResponseBody
    public ResponseVo dashboard(){
        DashBoard dashBoard = loginService.queryDashBoard();
        return ResponseUtil.success(dashBoard);
    }

    @RequestMapping("/auth/logout")
    @ResponseBody
    public ResponseVo logout(){
        SecurityUtils.getSubject().logout();
        return ResponseUtil.fail(
          null,"注销成功",0);
    }
}
