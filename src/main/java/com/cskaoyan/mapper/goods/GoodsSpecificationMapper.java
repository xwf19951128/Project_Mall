package com.cskaoyan.mapper.goods;

import com.cskaoyan.bean.admin.goods.GoodsSpecification;
import com.cskaoyan.bean.admin.goods.GoodsSpecificationExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoodsSpecificationMapper {
    long countByExample(GoodsSpecificationExample example);

    int deleteByExample(GoodsSpecificationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSpecification record);

    int insertSelective(GoodsSpecification record);

    List<GoodsSpecification> selectByExample(GoodsSpecificationExample example);

    GoodsSpecification selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsSpecification record, @Param("example") GoodsSpecificationExample example);

    int updateByExample(@Param("record") GoodsSpecification record, @Param("example") GoodsSpecificationExample example);

    int updateByPrimaryKeySelective(GoodsSpecification record);

    int updateByPrimaryKey(GoodsSpecification record);

    int insertSpecifications(@Param("goodsSpecificationMapList") List<Map<String, Object>> goodsSpecificationMapList);

    int updateSpecifications(@Param("goodsSpecificationMapList")List<Map<String, Object>> goodsSpecificationMapList);
}