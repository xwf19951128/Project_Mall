package com.cskaoyan.controller.wx.coreservice;

import com.cskaoyan.bean.admin.spread.MessageBean;
import com.cskaoyan.service.wx.coreservice.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/groupon")
public class UserGrouponController {
    @Autowired
    CoreService coreService;
    @RequestMapping("/list")
    public MessageBean showCouponList(int page,int size,short type){
        return coreService.showGrouponList(page,size,type);
    }
}
