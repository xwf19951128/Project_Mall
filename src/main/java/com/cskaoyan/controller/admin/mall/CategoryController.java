package com.cskaoyan.controller.admin.mall;

import com.cskaoyan.bean.admin.mall.category.Category;
import com.cskaoyan.bean.admin.mall.category.CategoryFirstClass;
import com.cskaoyan.bean.admin.mall.category.Label;
import com.cskaoyan.service.admin.mall.CategoryService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("admin/category")
@Api(tags = "category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/list")
    @ApiOperation(value = "条件查询和分页")
    public ResponseVo getCategory() {
        List<CategoryFirstClass> data = categoryService.getCategory();
        return ResponseUtil.success(data);
    }

    @RequestMapping("/l1")
    public ResponseVo getL1() {
        List<Label> data =  categoryService.getLabel();
        return ResponseUtil.success(data);
    }

    @RequestMapping("update")
    public ResponseVo updateCategory(@RequestBody CategoryFirstClass categoryFirstClass) {
        categoryFirstClass = categoryService.updateCategoryById(categoryFirstClass);
        return ResponseUtil.success(categoryFirstClass);
    }

    @RequestMapping("create")
    public ResponseVo createCategory(@RequestBody Category category) {
        category.setSortOrder((byte)(Math.ceil(Math.random() * 100)));
        category.setDeleted(false);
        category.setAddTime(new Date());
        category.setUpdateTime((new Date()));
        categoryService.createCategory(category);
        return ResponseUtil.success(category);
    }

    @RequestMapping("delete")
    public ResponseVo deleteCategory(@RequestBody Category category) {
        categoryService.deleteCategoryById(category.getId());
        return ResponseUtil.success(null);
    }

}
