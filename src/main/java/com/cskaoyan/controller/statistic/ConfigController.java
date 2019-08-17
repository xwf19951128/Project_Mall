package com.cskaoyan.controller.statistic;


import com.cskaoyan.bean.statistic.LiteMall;
import com.cskaoyan.service.statistic.ConfigService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin/config")
public class ConfigController {

    @Autowired
    ConfigService configService;

    @RequestMapping("/mall")
    public ResponseVo configmall(){
        //获取所有的和mall相关的系统配置JavaBean，一共有4个
        List<LiteMall> liteMallList = configService.queryConfigMall();
        //接下来就要把这四条记录的name和value取出来，以键值对的形式赋值给一个新的JavaBean传递到前端，需要遍历集合
        HashMap<String, String> data = new HashMap<>();
        for (LiteMall liteMall :liteMallList){
            data.put(liteMall.getKeyName(),liteMall.getKeyValue());
        }
        return ResponseUtil.success(data);
    }

    @RequestMapping("/express")
    public ResponseVo configexpress(){
        //获取所有的和mall相关的系统配置JavaBean，一共有4个
        List<LiteMall> liteMallList = configService.queryConfigExpress();
        //接下来就要把这四条记录的name和value取出来，以键值对的形式赋值给一个新的JavaBean传递到前端，需要遍历集合
        HashMap<String, String> data = new HashMap<>();
        for (LiteMall liteMall :liteMallList){
            data.put(liteMall.getKeyName(),liteMall.getKeyValue());
        }
        return ResponseUtil.success(data);
    }

    @RequestMapping("/order")
    public ResponseVo configorder(){
        //获取所有的和mall相关的系统配置JavaBean，一共有4个
        List<LiteMall> liteMallList = configService.queryConfigOrder();
        //接下来就要把这四条记录的name和value取出来，以键值对的形式赋值给一个新的JavaBean传递到前端，需要遍历集合
        HashMap<String, String> data = new HashMap<>();
        for (LiteMall liteMall :liteMallList){
            data.put(liteMall.getKeyName(),liteMall.getKeyValue());
        }
        return ResponseUtil.success(data);
    }

    @RequestMapping("/wx")
    public ResponseVo configwx(){
        //获取所有的和mall相关的系统配置JavaBean，一共有4个
        List<LiteMall> liteMallList = configService.queryConfigWx();
        //接下来就要把这四条记录的name和value取出来，以键值对的形式赋值给一个新的JavaBean传递到前端，需要遍历集合
        HashMap<String, String> data = new HashMap<>();
        for (LiteMall liteMall :liteMallList){
            data.put(liteMall.getKeyName(),liteMall.getKeyValue());
        }
        return ResponseUtil.success(data);
    }
}
