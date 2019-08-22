package com.cskaoyan.controller.wx.catalog;

import com.cskaoyan.bean.admin.mall.category.Category;
import com.cskaoyan.service.admin.mall.CategoryService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/wx/catalog")
public class WxCatalogController {
    
    @Autowired
    CategoryService categoryService;

    /**
     * 查询分类页面
     * @return
     */
    @RequestMapping("/index")
    public ResponseVo listCategories(){
        HashMap<Object, Object> data = new HashMap<>(3);
/*        List<CategoryFirstClass> categoryFirstClassList = categoryService.getCategory();
        List<Category> categoryList = new ArrayList<>();
        for (CategoryFirstClass categoryFirstClass : categoryFirstClassList) {
            Category firstLevelCategory = new Category();
            firstLevelCategory.setId(categoryFirstClass.getId());
            firstLevelCategory.setName(categoryFirstClass.getName());
            firstLevelCategory.setKeywords(categoryFirstClass.getKeywords());
            firstLevelCategory.setDesc(categoryFirstClass.getDesc());
            firstLevelCategory.setIconUrl(categoryFirstClass.getIconUrl());
            firstLevelCategory.setPicUrl(categoryFirstClass.getPicUrl());
            firstLevelCategory.setLevel(categoryFirstClass.getLevel());
        }*/
        List<Category> firstLevelCategoryList = categoryService.listFirstLevelCategories();
        if(firstLevelCategoryList == null){
            return ResponseUtil.fail(null, "firstLevelCategoryList查询失败", 502);
        }
        Integer currentCategoryId = firstLevelCategoryList.get(0).getId();
        Category currentCategory = categoryService.getCategoryById(currentCategoryId);
        List<Category> secondLevelCategoryList = categoryService.listSecondLevelCategories();
        if(secondLevelCategoryList == null){
            return ResponseUtil.fail(null, "secondLevelCategoryList查询失败", 502);
        }
        data.put("categoryList", firstLevelCategoryList);
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", secondLevelCategoryList);
        return ResponseUtil.success(data);
    }

    @RequestMapping("/current")
    public ResponseVo getCurrentCategory(int id){
        HashMap<Object, Object> data = new HashMap<>(2);
        Category currentCategory = categoryService.getCategoryById(id);
        List<Category> currentSubCategoryList = categoryService.listSecondLevelCategoriesByCategoryId(id);
        if(currentSubCategoryList == null){
            return ResponseUtil.fail(null, "currentSubCategoryList查询失败", 502);
        }
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategoryList);
        return ResponseUtil.success(data);
    }
}
