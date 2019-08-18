package com.cskaoyan.service.mall.impl;

import com.cskaoyan.bean.mall.category.CategoryFirstClass;
import com.cskaoyan.bean.mall.category.Label;
import com.cskaoyan.mapper.mall.CategoryMapper;
import com.cskaoyan.service.mall.CategoryService;
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
}
