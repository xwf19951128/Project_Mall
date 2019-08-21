package com.cskaoyan.service.admin.goods;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.goods.PageParams4Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsService {

    long countTotalGoodsCount();

    List<Goods> listPageGoods(PageParams4Goods pageParams4Goods);

    List<Goods> listPageGoodsByGoodsSn(PageParams4Goods pageParams4Goods, String goodsSn);

    List<Goods> listPageGoodsByName(PageParams4Goods pageParams4Goods, String name);

    List<Goods> listPageGoodsByGoodsSnAndName(PageParams4Goods pageParams4Goods, String goodsSn, String name);

    Goods getSingleGoodsById(int id);

    int deleteSingleGoodsById(Integer goodsId);

    int insertSingleGoods(@Param("goodsMap") Map<String, Object> goodsMap);

    int updateSingleGoods(Map<String, Object> goodsMap);
}
