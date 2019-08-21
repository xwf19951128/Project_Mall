package com.cskaoyan.controller.admin.mall;

import com.cskaoyan.bean.admin.mall.brand.Brand;
import com.cskaoyan.bean.admin.mall.brand.BrandPage;
import com.cskaoyan.service.admin.mall.BrandService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("admin/brand")
@Api(tags = "brand", description = "品牌制造商")
public class BrandController {

    @Autowired
    BrandService brandService;

    /*品牌分页*/
    @RequestMapping("list")
    @ApiOperation(value = "条件查询和分页")
    public ResponseVo getBrandList(int page, int limit, String sort, String order, Integer id, String name) {
        StringBuilder term = new StringBuilder();
        if (name != null) {
            term.append("%").append(name).append("%");
        }
        BrandPage brandPage = new BrandPage();
        String orderBy = sort + " " + order;
        PageHelper.startPage(page,limit,orderBy);
        List<Brand> brandList = brandService.getBrandList(id,term.toString());
        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);
        brandPage.setItems(brandList);
        brandPage.setTotal((int)pageInfo.getTotal());
        return ResponseUtil.success(brandPage);
    }

    /*品牌更新*/
    @RequestMapping("update")
    public ResponseVo updateBrandById(@RequestBody Brand brand) {
        System.out.println(brand);
        brand.setUpdateTime(new Date());
        brand = brandService.updateBrandById(brand);
        return ResponseUtil.success(brand);
    }

    @RequestMapping("create")
    public ResponseVo createBrand(@RequestBody Brand brand) {
        brand.setAddTime(new Date());
        brand.setUpdateTime(new Date());
        brandService.insertBrand(brand);
        return ResponseUtil.success(brand);
    }

    @RequestMapping("delete")
    public ResponseVo deleteBrand(@RequestBody Brand brand) {
        brandService.deleteBrandById(brand.getId());
        return ResponseUtil.success(null);
    }
}
