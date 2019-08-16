package com.cskaoyan.controller.statistic;

import com.cskaoyan.util.ResponseVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/stat")
public class StatisticController {
    @RequestMapping("/user")
    public ResponseVo user(){
        return null;
    }
    @RequestMapping("/order")
    public ResponseVo order(){
        return null;
    }
    @RequestMapping("/goods")
    public ResponseVo goods(){
        return null;
    }
}
