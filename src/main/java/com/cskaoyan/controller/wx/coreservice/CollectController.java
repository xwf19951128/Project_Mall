package com.cskaoyan.controller.wx.coreservice;

import com.cskaoyan.bean.admin.spread.MessageBean;
import com.cskaoyan.service.wx.coreservice.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    public MessageBean addOrDeleteCollect(@RequestBody Map map, HttpServletRequest request){
        int valueId= (int) map.get("valueId");
        return coreService.insertCollect(valueId,request);
    }
}
