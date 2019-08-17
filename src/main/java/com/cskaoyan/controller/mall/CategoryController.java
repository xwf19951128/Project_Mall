package com.cskaoyan.controller.mall;

import com.cskaoyan.bean.mall.category.CategoryFirstClass;
import com.cskaoyan.bean.mall.category.Label;
import com.cskaoyan.service.mall.CategoryService;
import com.cskaoyan.utils.ResponseUtil;
import com.cskaoyan.utils.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/list")
    public ResponseVo getCategory() {
        List<CategoryFirstClass> data = categoryService.getCategory();
        return ResponseUtil.success(data);
    }

    @RequestMapping("/l1")
    public ResponseVo getL1() {
        List<Label> data =  categoryService.getLabel();
        return ResponseUtil.success(data);
    }

}
