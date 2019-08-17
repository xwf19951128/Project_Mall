package com.cskaoyan.controller.mall;

import com.cskaoyan.bean.mall.brand.Brand;
import com.cskaoyan.bean.mall.brand.BrandPage;
import com.cskaoyan.service.mall.BrandService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("admin/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    /*品牌分页*/
    @RequestMapping("list")
    public ResponseVo getBrandList(int page, int limit, String sort, String order) {
        BrandPage brandPage = new BrandPage();
        String orderBy = sort + " " + order;
        PageHelper.startPage(page,limit,orderBy);
        List<Brand> brandList = brandService.getBrandList();
        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);
        brandPage.setItems(brandList);
        brandPage.setTotal((int)pageInfo.getTotal());
        return ResponseUtil.success(brandPage);
    }

    /*品牌更新*/
    @RequestMapping("update")
    public ResponseVo updateBrandById(Brand brand) {
        System.out.println(brand);
        brand.setUpdateTime(new Date());
        brand = brandService.updateBrandById(brand);
        return ResponseUtil.success(brand);
    }

    @RequestMapping("create")
    public ResponseVo createBrand(Brand brand) {
        brand.setAddTime(new Date());
        brand.setUpdateTime(new Date());
        brand = brandService.insertBrand(brand);
        return ResponseUtil.success(brand);
    }

}
