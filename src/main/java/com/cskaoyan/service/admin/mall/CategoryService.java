package com.cskaoyan.service.admin.mall;

import com.cskaoyan.bean.admin.mall.category.Category;
import com.cskaoyan.bean.admin.mall.category.CategoryFirstClass;
import com.cskaoyan.bean.admin.mall.category.Label;

import java.util.List;

public interface CategoryService {
    List<CategoryFirstClass> getCategory();

    List<Label> getLabel();

    CategoryFirstClass updateCategoryById(CategoryFirstClass categoryFirstClass);

    void createCategory(Category category);

    Category getCategoryById(Integer id);

    void deleteCategoryById(Integer id);

    List<Category> listFirstLevelCategories();

    List<Category> listSecondLevelCategories();

    List<Category> listSecondLevelCategoriesByCategoryId(int categoryId);
}
