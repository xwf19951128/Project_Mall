package com.cskaoyan.controller.admin.spread;

import com.cskaoyan.bean.admin.spread.ListDate;
import com.cskaoyan.bean.admin.spread.MallTopic;
import com.cskaoyan.bean.admin.spread.MessageBean;
import com.cskaoyan.service.admin.speard.SpreadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Controller
@RequestMapping("admin/topic/")
@Api(tags = "Created By Mr.Xu",description = "主题系统")
@RestController
public class TopicController {
    @Autowired
    SpreadService spreadService;
    @ApiOperation(value = "显示页面，包括模糊查询")
    @RequestMapping(value = "/list")
    public MessageBean<ListDate<MallTopic>> showPage(int page, int limit, String title, String subtitle){
        return spreadService.showTopicListByPage(page,limit,title,subtitle);
    }
    @ApiOperation(value = "更新单个主题信息")
    @RequestMapping(value = "/update")
    public MessageBean<MallTopic> updateRecord(@RequestBody MallTopic ad){
        return spreadService.updateRecord(ad);
    }
    @ApiOperation(value = "增加新主题")
    @RequestMapping(value = "/create")
    public MessageBean<MallTopic> addRecord( @RequestBody MallTopic ad){
//        public MessageBean<MallTopic> addRecord(@RequestBody Map<String,String> ad){
        System.out.println(ad);
        return spreadService.addRecord(ad);
    }

    @ApiOperation(value = "删除主题")
    @RequestMapping(value = "/delete")
    public MessageBean<String> deleteRecord(@RequestBody MallTopic ad){
        return spreadService.deleteRecord(ad);
    }
}
