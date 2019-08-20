package com.cskaoyan.controller.admin.login;

import com.cskaoyan.bean.admin.login.Admin;
import com.cskaoyan.bean.admin.login.AdminInfo;
import com.cskaoyan.bean.admin.login.DashBoard;
import com.cskaoyan.bean.admin.login.Password;
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
import java.util.Date;
import java.util.List;

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
    //修改密码
    @RequestMapping("/profile/password")
    @ResponseBody
    public ResponseVo password(@RequestBody Password password){
        //首先利用shiro，获取当前的用户名
        String principal = (String) SecurityUtils.getSubject().getPrincipal();
        System.out.println(principal);
        //然后要判断旧密码是否正确
        String oldPassword = password.getOldPassword();
        List<Admin> admins = loginService.queryPasswordByName(principal);
        if (!admins.get(0).getPassword().equals(oldPassword)){
            return ResponseUtil.fail(null,"旧密码错误！",605);
        }
        //然后要判断新密码是否和旧密码相同
        String newPassword = password.getNewPassword();
        if (newPassword.equals(oldPassword)){
            return ResponseUtil.fail(null,"新旧密码不能相同！",605);
        }
        //确认无误后再进行修改,同时要修改当前用户的updatetime字段为当前时间
        Admin admin = admins.get(0);
        admin.setPassword(newPassword);
        Date date = new Date();
        admin.setUpdateTime(date);
        int i = loginService.updatePasswordAndTime(admin);
        if (i==1){
            return ResponseUtil.success(null);
        }else {
            return ResponseUtil.fail(null,"修改失败",0);
        }
    }
}
