package com.cskaoyan.controller.wx.coreservice;

import com.cskaoyan.bean.admin.spread.MessageBean;
import com.cskaoyan.service.wx.coreservice.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wx/groupon")
public class UserGrouponController {
    @Autowired
    CoreService coreService;
    @RequestMapping("/my")
    public MessageBean showCouponList(short showType , HttpServletRequest request){
        return coreService.showGrouponList(showType,request);
    }
}
