package com.cskaoyan.mapper.mall;


import com.cskaoyan.bean.mall.category.Category;
import com.cskaoyan.bean.mall.category.CategoryExample;
import com.cskaoyan.bean.mall.category.CategoryFirstClass;
import com.cskaoyan.bean.mall.category.Label;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<CategoryFirstClass> getCategory();

    List<Label> getLabel();

}