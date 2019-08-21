package com.cskaoyan.mapper.goods;

import com.cskaoyan.bean.admin.goods.GoodsProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoodsProductMapper {

    List<GoodsProduct> listGoodsProductsByGoodsId(@Param("goodsId") int id);

    int insertGoodsProduct(@Param("goodsProductMapList") List<Map<String, Object>> goodsProductMapList, @Param("lastInsertGoodsId") Integer lastInsertGoodsId, @Param("otherAttributeMap") Map<String, Object> otherAttributeMap);
}