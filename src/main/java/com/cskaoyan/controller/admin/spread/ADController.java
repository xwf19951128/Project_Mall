package com.cskaoyan.controller.admin.spread;

import com.cskaoyan.bean.admin.spread.MallAD;
import com.cskaoyan.bean.admin.spread.MessageBean;
import com.cskaoyan.bean.admin.spread.ListDate;
import com.cskaoyan.service.admin.speard.SpreadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("admin/ad")
@Api(tags = "Created By Mr.Xu",description = "广告系统")
@RestController
public class ADController {
    @Autowired
    SpreadService spreadService;
    @ApiOperation(value = "显示页面，包括模糊查询")
    @RequestMapping(value = "/list")
    public MessageBean<ListDate<MallAD>> showPage(int page, int limit,String content,String name){
        return spreadService.showADListByPage(page,limit,content,name);
    }
    @ApiOperation(value = "更新单个广告信息")
    @RequestMapping(value = "/update")
    public MessageBean<MallAD> updateRecord(@RequestBody MallAD ad){
        return spreadService.updateRecord(ad);
    }
    @ApiOperation(value = "增加新广告")
    @RequestMapping(value = "/create")
    public MessageBean<MallAD> addRecord(@RequestBody MallAD ad){
        return spreadService.addRecord(ad);
    }
    @ApiOperation(value = "删除广告")
    @RequestMapping(value = "/delete")
    public MessageBean<String> deleteRecord(@RequestBody MallAD ad){
        return spreadService.deleteRecord(ad);
    }
}
