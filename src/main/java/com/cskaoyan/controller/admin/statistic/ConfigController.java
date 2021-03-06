package com.cskaoyan.controller.admin.statistic;


import com.cskaoyan.bean.admin.statistic.LiteMall;
import com.cskaoyan.service.admin.statistic.ConfigService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/config")
public class ConfigController {

    @Autowired
    ConfigService configService;

    //在本模块中，get请求是查询所有记录，post请求是修改记录

    @GetMapping("/mall")
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

    @PostMapping("/mall")
    public ResponseVo updatemall(@RequestBody HashMap<String,String> map){
        //把四个键值对以Map的形式接收，更加方法
        //遍历这个Map。把四个键值对分别更新即可
        Set<String> keys = map.keySet();
        for (String key :keys) {
            String value = map.get(key);
            configService.updateKeyValueByName(key,value);
        }
        return ResponseUtil.success();
    }

    @GetMapping("/express")
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

    @PostMapping("/express")
    public ResponseVo updateexpress(@RequestBody HashMap<String,String> map){
        //把四个键值对以Map的形式接收，更加方法
        //遍历这个Map。把四个键值对分别更新即可
        Set<String> keys = map.keySet();
        for (String key :keys) {
            String value = map.get(key);
            configService.updateKeyValueByName(key,value);
        }
        return ResponseUtil.success();
    }

    @GetMapping("/order")
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

    @PostMapping("/order")
    public ResponseVo updateorder(@RequestBody HashMap<String,String> map){
        //把四个键值对以Map的形式接收，更加方法
        //遍历这个Map。把四个键值对分别更新即可
        Set<String> keys = map.keySet();
        for (String key :keys) {
            String value = map.get(key);
            configService.updateKeyValueByName(key,value);
        }
        return ResponseUtil.success();
    }

    @GetMapping("/wx")
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
    @PostMapping("/wx")
    public ResponseVo updatewx(@RequestBody HashMap<String,String> map){
        //把四个键值对以Map的形式接收，更加方法
        //遍历这个Map。把四个键值对分别更新即可
        Set<String> keys = map.keySet();
        for (String key :keys) {
            String value = map.get(key);
            configService.updateKeyValueByName(key,value);
        }
        return ResponseUtil.success();
    }


}
