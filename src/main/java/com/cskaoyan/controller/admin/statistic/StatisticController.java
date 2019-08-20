package com.cskaoyan.controller.admin.statistic;

import com.cskaoyan.bean.admin.statistic.*;
import com.cskaoyan.bean.admin.statistic.*;
import com.cskaoyan.service.admin.statistic.StatService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/stat")
@Api(tags = "Statistic-Controller",description = "这是统计模块的方法")
public class StatisticController {

    @Autowired
    StatService statService;

    @RequestMapping("/user")
    @ApiOperation(value = "user方法",notes = "这是用于统计用于的方法")
    public ResponseVo user(ConfigMall configMall){
        List<StatUser> statUsers = statService.statUser();
        StatisticVo<StatUser> statisticVo = new StatisticVo<>();
        statisticVo.setRows(statUsers);
        statisticVo.setColumns(new String[]{"day","users"});
        ResponseVo success = ResponseUtil.success(statisticVo);
        return success;
    }

    @RequestMapping("/order")
    public ResponseVo order(){
        List<StatOrder> statOrders = statService.statOrder();
        StatisticVo<StatOrder> statisticVo = new StatisticVo<>();
        statisticVo.setRows(statOrders);
        statisticVo.setColumns(new String[]{"day","orders","customers","amount","pcr"});
        ResponseVo success = ResponseUtil.success(statisticVo);
        return success;
    }

    @RequestMapping("/goods")
    public ResponseVo goods(){
        List<StatGoods> statGoods = statService.statGoods();
        StatisticVo<StatGoods> statisticVo = new StatisticVo<>();
        statisticVo.setRows(statGoods);
        statisticVo.setColumns(new String[]{"day","orders","products","amount"});
        ResponseVo responseVo = ResponseUtil.success(statisticVo);
        return responseVo;
    }
}
