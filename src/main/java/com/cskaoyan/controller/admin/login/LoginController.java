package com.cskaoyan.controller.admin.login;

import com.cskaoyan.bean.admin.login.Admin;
import com.cskaoyan.bean.admin.login.AdminInfo;
import com.cskaoyan.bean.admin.login.DashBoard;
import com.cskaoyan.bean.admin.login.Password;
import com.cskaoyan.bean.admin.system.Log;
import com.cskaoyan.config.TypeToken;
import com.cskaoyan.service.admin.login.LoginService;
import com.cskaoyan.service.admin.system.LogService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    LogService logService;

    @RequestMapping("/auth/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }

    @RequestMapping("/auth/login")
    @ResponseBody
    public ResponseVo login(@RequestBody Admin admin, HttpServletRequest request){
        String username = admin.getUsername();
        String password =admin.getPassword();
        ResponseVo responseVo =null;
        Subject subject = SecurityUtils.getSubject();
        TypeToken token = new TypeToken(username, password,"admin");
        try {
            subject.login(token);
            Serializable id = subject.getSession().getId();
            //修改当前用户的last_login时间，以及登录IP！
            Date date = new Date();
            String remoteAddr = request.getRemoteAddr();
            List<Admin> admins = loginService.queryPasswordByName(username);
            Admin admin1 = admins.get(0);
            admin1.setLastLoginIp(remoteAddr);
            admin1.setLastLoginTime(date);
            loginService.updateIPAndLastTime(admin1);
            responseVo = ResponseUtil.success(id);
            //插入登录状态
            insertLogInAuth(admin1.getUsername(),remoteAddr,"登录");

        } catch (AuthenticationException e) {
            responseVo = ResponseUtil.fail(null, "用户名或密码不正确", 605);
        }
        return responseVo;
    }

    private int insertLogInAuth(String username, String remoteAddr, String desc) {
        Log log = new Log();
        log.setType(1);
        log.setStatus(true);
        log.setAction(desc);
        log.setAdmin(username);
        log.setIp(remoteAddr);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatedDate = sdf.format(new Date());
        //需要放入一个string类型，放入数据库，应该转为date类型
        log.setAddTime(formatedDate);
        log.setUpdateTime(formatedDate);

        return logService.insertLog(log);
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
    public ResponseVo logout(HttpServletRequest request){
        //SecurityUtils.getSubject().logout();
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        subject.logout();

        //插入注销状态
        insertLogInAuth(username,request.getRemoteAddr(),"注销");

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
