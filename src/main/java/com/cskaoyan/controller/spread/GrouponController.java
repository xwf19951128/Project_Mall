package com.cskaoyan.controller.spread;

import com.cskaoyan.bean.spread.*;
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
@RequestMapping("admin/groupon")
@Api(tags = "Created By Mr.Xu",description = "团购规则")
@RestController
public class GrouponController {
    @Autowired
    SpreadService spreadService;
    @ApiOperation(value = "显示页面，包括模糊查询")
    @RequestMapping(value = "/list")
    public MessageBean<ListDate<MallGrouponRule>> showPage(int page, int limit, String goodsId){
        return spreadService.showGrouponRuleListByPage(page,limit,goodsId);
    }
    @ApiOperation(value = "显示团购订单？页面，包括模糊查询")
    @RequestMapping(value = "/listRecord")
    public MessageBean<ListDate<GrouponInfo>> showGrouponInfoPage(int page, int limit, String content, String name){
        return spreadService.showGrouponInfoListByPage(page,limit,content,name);
    }
    @ApiOperation(value = "更新单个团购规则")
    @RequestMapping(value = "/update")
    public MessageBean<MallGrouponRule> updateRecord(@RequestBody MallGrouponRule ad){
        return spreadService.updateRecord(ad);
    }
    @ApiOperation(value = "增加新团购规则")
    @RequestMapping(value = "/create")
    public MessageBean<MallGrouponRule> addRecord(@RequestBody MallGrouponRule ad){
        System.out.println(ad);
        return spreadService.addRecord(ad);
    }
    @ApiOperation(value = "删除团购规则")
    @RequestMapping(value = "/delete")
    public MessageBean<String> deleteRecord(@RequestBody MallGrouponRule ad){
        return spreadService.deleteRecord(ad);
    }
}
