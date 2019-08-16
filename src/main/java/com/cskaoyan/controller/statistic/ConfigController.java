package com.cskaoyan.controller.statistic;

import com.cskaoyan.bean.statistic.ConfigMall;
import com.cskaoyan.bean.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/admin/config")
public class ConfigController {

    @Autowired


    @RequestMapping("/mall")
    public ResponseVo configmall(){
        ResponseVo vo = new ResponseVo();
        vo.setErrno(0);
        vo.setErrmsg("成功");
        ConfigMall configMall = new ConfigMall();

    }
}
