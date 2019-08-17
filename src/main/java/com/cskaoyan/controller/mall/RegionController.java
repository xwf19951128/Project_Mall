package com.cskaoyan.controller.mall;

import com.cskaoyan.bean.mall.region.Province;
import com.cskaoyan.service.mall.RegionService;
import com.cskaoyan.utils.ResponseUtil;
import com.cskaoyan.utils.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class RegionController {

    @Autowired
    RegionService regionService;



    /*行政区域*/
    @RequestMapping("region/list")
    public ResponseVo getRegion() {
        List<Province> data = regionService.getRegion();
        return ResponseUtil.success(data);
    }





}
