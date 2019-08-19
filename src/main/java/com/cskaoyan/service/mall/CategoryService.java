package com.cskaoyan.service.mall;

import com.cskaoyan.bean.mall.category.Category;
import com.cskaoyan.bean.mall.category.CategoryFirstClass;
import com.cskaoyan.bean.mall.category.Label;

import java.util.List;

public interface CategoryService {
    List<CategoryFirstClass> getCategory();

    List<Label> getLabel();

    CategoryFirstClass updateCategoryById(CategoryFirstClass categoryFirstClass);

    void createCategory(Category category);

    Category getCategoryById(Integer id);

    void deleteCategoryById(Integer id);
}
