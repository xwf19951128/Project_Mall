package com.cskaoyan.controller.spread;

import com.cskaoyan.bean.spread.ListDate;
import com.cskaoyan.bean.spread.MallAD;
import com.cskaoyan.bean.spread.MallCoupon;
import com.cskaoyan.bean.spread.MessageBean;
import com.cskaoyan.service.speard.SpreadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("admin/coupon")
@Api(tags = "Created By Mr.Xu",description = "优惠卷系统")
@RestController
public class CouponController {
    @Autowired
    SpreadService spreadService;
    @ApiOperation(value = "显示页面，包括模糊查询")
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public MessageBean<ListDate<MallCoupon>> showPage(int page, int limit, String content, String name){
        return spreadService.showCouponListByPage(page,limit,content,name);
    }
    @ApiOperation(value = "查看单个优惠卷信息,返回一个空的ListDate")
    @RequestMapping(value = "listuser",method = RequestMethod.POST)
    public MessageBean<ListDate<String>> shownullPage(int page, int limit,String content,String name,String couponId){
        return spreadService.showSingleCouponByPage(page,limit,content,name ,couponId);
    }
    @ApiOperation(value = "查看单个优惠卷信息")
    @RequestMapping(value = "read",method = RequestMethod.GET)
    public MessageBean<MallCoupon> showPage(String couponId){
        return spreadService.readMallCouponInfo(couponId);
    }
    @ApiOperation(value = "更新优惠卷效果")
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public MessageBean<MallCoupon> updateRecord(@RequestBody MallCoupon ad){
        return spreadService.updateRecord(ad);
    }
    @ApiOperation(value = "增加优惠卷")
    @RequestMapping(value = "create",method = RequestMethod.POST)
    public MessageBean<MallCoupon> addRecord(@RequestBody MallCoupon ad){
        return spreadService.addRecord(ad);
    }
    @ApiOperation(value = "删除优惠卷")
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public MessageBean<String> deleteRecord(@RequestBody MallCoupon ad){
        return spreadService.deleteRecord(ad);
    }
}
