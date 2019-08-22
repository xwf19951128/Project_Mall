package com.cskaoyan.controller.wx.coreservice;

import com.cskaoyan.bean.admin.spread.MessageBean;
import com.cskaoyan.service.wx.coreservice.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wx/collect")
public class CollectController {
    @Autowired
    CoreService coreService;
    @RequestMapping("/list")
    public MessageBean showCouponList(int page, int size, short type, HttpServletRequest request){
        return coreService.showCollectList(page,size,type,request);
    }
    @RequestMapping("/addordelete")
    public MessageBean addOrDeleteCollect(int valueId, HttpServletRequest request){
        return coreService.insertCollect(valueId,request);
    }
}
