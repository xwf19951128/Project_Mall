package com.cskaoyan.controller.login;

import com.cskaoyan.bean.login.Admin;
import com.cskaoyan.bean.login.AdminInfo;
import com.cskaoyan.bean.login.DashBoard;
import com.cskaoyan.service.login.LoginService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping("/auth/login")
    public ResponseVo login(@RequestBody Admin admin, HttpSession session){
        List<Admin> admins = loginService.queryAdminByUsernameAndPassword(admin);
        ResponseVo responseVo =null;
        if (admins.size()==0){
            responseVo = ResponseUtil.fail(null, "用户帐号或密码不正确", 605);
        }else {
            UUID uuid = UUID.randomUUID();
            responseVo = ResponseUtil.success(uuid);
            session.setAttribute("admin",admin);
        }
        return responseVo;
    }

    @RequestMapping("/auth/info")
    public ResponseVo info(HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        AdminInfo adminInfo = loginService.queryAdminInfoByUsername("admin123");
        return ResponseUtil.success(adminInfo);
    }

    @RequestMapping("/dashboard")
    public ResponseVo dashboard(){
        DashBoard dashBoard = loginService.queryDashBoard();
        return ResponseUtil.success(dashBoard);
    }

    @RequestMapping("/auth/logout")
    public ResponseVo logout(){
        return ResponseUtil.fail(null,"请登录",501);
    }
}
