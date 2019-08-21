package com.cskaoyan.service.admin.mall.impl;

import com.cskaoyan.bean.admin.mall.category.Category;
import com.cskaoyan.bean.admin.mall.category.CategoryExample;
import com.cskaoyan.bean.admin.mall.category.CategoryFirstClass;
import com.cskaoyan.bean.admin.mall.category.Label;
import com.cskaoyan.mapper.mall.CategoryMapper;
import com.cskaoyan.service.admin.mall.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryFirstClass> getCategory() {
        return categoryMapper.getCategory();
    }

    @Override
    public List<Label> getLabel() {
        return categoryMapper.getLabel();
    }

    @Override
    public CategoryFirstClass updateCategoryById(CategoryFirstClass categoryFirstClass) {
        int i = categoryMapper.updateCategoryById(categoryFirstClass);
        if (i>0) {
            return categoryMapper.selectById(categoryFirstClass.getId());
        }
        return null;
    }

    @Override
    public void createCategory(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteCategoryById(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Category> listFirstLevelCategories() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andPidEqualTo(0);
        return categoryMapper.selectByExample(categoryExample);
    }

    @Override
    public List<Category> listSecondLevelCategories() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andPidNotEqualTo(0);
        return categoryMapper.selectByExample(categoryExample);
    }

    @Override
    public List<Category> listSecondLevelCategoriesByCategoryId(int categoryId) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andPidEqualTo(categoryId);
        return categoryMapper.selectByExample(categoryExample);
    }
}
