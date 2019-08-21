package com.cskaoyan.controller.wx.coreservice;

import com.cskaoyan.bean.admin.spread.MessageBean;
import com.cskaoyan.bean.wx.coreservice.ListDateWX;

import com.cskaoyan.service.wx.coreservice.CoreService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wx/coupon")
public class UserCouponController {
    @Autowired
    CoreService coreService;
    @RequestMapping("/mylist")
    public MessageBean showCouponList(int page,int size,short status){
        return coreService.showCouponList(page,size,status);
    }
}
