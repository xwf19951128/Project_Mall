package com.cskaoyan.mapper.goods;

import com.cskaoyan.bean.admin.goods.GoodsAttribute;
import com.cskaoyan.bean.admin.goods.GoodsAttributeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface GoodsAttributeMapper {
    long countByExample(GoodsAttributeExample example);

    int deleteByExample(GoodsAttributeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsAttribute record);

    int insertSelective(GoodsAttribute record);

    List<GoodsAttribute> selectByExample(GoodsAttributeExample example);

    GoodsAttribute selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsAttribute record, @Param("example") GoodsAttributeExample example);

    int updateByExample(@Param("record") GoodsAttribute record, @Param("example") GoodsAttributeExample example);

    int updateByPrimaryKeySelective(GoodsAttribute record);

    int updateByPrimaryKey(GoodsAttribute record);

    int insertGoodsAttributeByLastGoodsId(@Param("goodsAttributeList") List<GoodsAttribute> goodsAttributeList, @Param("lastInsertGoodsId") Integer lastInsertGoodsId, @Param("otherAttributeMap") HashMap<String, Object> otherAttributeMap);

    List<GoodsAttribute> selectGoodsAttributeByGoodId(@Param("id") Integer id);
}