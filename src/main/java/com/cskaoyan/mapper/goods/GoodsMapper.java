package com.cskaoyan.mapper.goods;

import com.cskaoyan.bean.goods.Goods;
import com.cskaoyan.bean.goods.PageParams4Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {

    @Select("select count(id) from cskaoyan_mall_goods")
    long countTotalGoodsCount();


    List<Goods> listAllGoods(/*@Param("limit") int limit, @Param("offset") int offset, */@Param("sort") String sort, @Param("order") String order);

}