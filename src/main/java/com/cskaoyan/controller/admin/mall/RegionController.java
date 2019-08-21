package com.cskaoyan.controller.admin.mall;

import com.cskaoyan.bean.admin.mall.region.Province;
import com.cskaoyan.service.admin.mall.RegionService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
@Api(tags = "region")
public class RegionController {

    @Autowired
    RegionService regionService;



    /*行政区域*/
    @RequestMapping("region/list")
    @ApiOperation(value = "条件查询和分页")
    public ResponseVo getRegion() {
        List<Province> data = regionService.getRegion();
        return ResponseUtil.success(data);
    }





}
