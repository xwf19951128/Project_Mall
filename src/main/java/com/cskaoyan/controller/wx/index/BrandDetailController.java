package com.cskaoyan.controller.wx.index;

import com.cskaoyan.bean.admin.mall.brand.Brand;
import com.cskaoyan.bean.wx.index.vo.WxBrandVo;
import com.cskaoyan.service.wx.index.BrandDetailService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 11:09 2019/8/22
 */
@RequestMapping("/wx")
@RestController
public class BrandDetailController {

    @Autowired
    BrandDetailService brandDetailService;

    @RequestMapping("/brand/detail")
    public ResponseVo BrandDetail(@RequestParam("id") int id){
        ResponseVo responseVo = new ResponseVo();
        Brand brands =brandDetailService.selectBrandById(id);

        WxBrandVo wxBrandVo = new WxBrandVo(brands);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        responseVo.setData(wxBrandVo);
        return  responseVo;
    }


}
