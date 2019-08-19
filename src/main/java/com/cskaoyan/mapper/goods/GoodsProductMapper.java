package com.cskaoyan.mapper.goods;

import com.cskaoyan.bean.goods.GoodsProduct;
import com.cskaoyan.bean.goods.GoodsProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsProductMapper {

    List<GoodsProduct> listGoodsProductsByGoodsId(@Param("goodsId")int id);
}