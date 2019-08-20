package com.cskaoyan.mapper.goods;

import com.cskaoyan.bean.admin.goods.GoodsProduct;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsProductMapper {

    List<GoodsProduct> listGoodsProductsByGoodsId(@Param("goodsId")int id);
}