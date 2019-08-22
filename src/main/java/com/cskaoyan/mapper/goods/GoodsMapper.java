package com.cskaoyan.mapper.goods;

import com.cskaoyan.bean.admin.goods.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoodsMapper {

    @Select("select count(id) from cskaoyan_mall_goods")
    long countTotalGoodsCount();

    Goods findGoods(@Param("goodsId") Integer id);

    List<Goods> listAllGoods(/*@Param("limit") int limit, @Param("offset") int offset, *//*@Param("sort") String sort, @Param("order") String order*/);

    List<Goods> listSearchGoodsByGoodsSn(@Param("sort") String sort, @Param("order") String order, @Param("like") String like);

    List<Goods> listSearchGoodsByName(@Param("sort") String sort, @Param("order") String order, @Param("like") String like);

    List<Goods> listSearchGoodsByGoodsSnAndName(@Param("sort") String sort, @Param("order") String order, @Param("like1") String like1, @Param("like2") String like2);

    Goods getSingleGoodsById(@Param("id") int id);

    int deleteSingleGoodsById(@Param("id") Integer goodsId);


    //查询热门商品和最新商品
    List<Goods> selectHotGoods();

    List<Goods> selectNewGoods();

    int insertSingleGoods(@Param("goodsMap") Map<String, Object> goodsMap);

    int updateSingleGoods(@Param("goodsMap")Map<String, Object> goodsMap);

}