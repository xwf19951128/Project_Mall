package com.cskaoyan.mapper.mall;

import com.cskaoyan.bean.mall.brand.Brand;
import com.cskaoyan.bean.mall.brand.BrandExample;
import io.swagger.models.auth.In;
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
    List<Brand> getBrandListById(@Param("id") Integer id);
    List<Brand> getBrandListByName(@Param("name") String name);
    List<Brand> getBrandListByIdAndName(@Param("id") Integer id,@Param("name") String name);

    int updateBrandById(@Param("brand") Brand brand);

    Brand queryBrandById(@Param("id") Integer id);

    Brand queryBrandByAddTime(@Param("addTime") Date addTime);
}