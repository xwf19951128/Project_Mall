package com.cskaoyan.mapper.mall;

import com.cskaoyan.bean.mall.brand.Brand;
import com.cskaoyan.bean.mall.brand.BrandExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BrandMapper {
    long countByExample(BrandExample example);

    int deleteByExample(BrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    List<Brand> selectByExample(BrandExample example);

    Brand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByExample(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

    List<Brand> getBrandList();

    int updateBrandById(@Param("brand") Brand brand);

    Brand queryBrandById(@Param("id") Integer id);

    Brand queryBrandByAddTime(@Param("addTime") Date addTime);
}