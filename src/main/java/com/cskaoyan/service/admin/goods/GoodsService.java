package com.cskaoyan.service.admin.goods;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.goods.PageParams4Goods;

import java.util.List;

public interface GoodsService {

    long countTotalGoodsCount();

    List<Goods> listPageGoods(PageParams4Goods pageParams4Goods);

    List<Goods> listPageGoodsByGoodsSn(PageParams4Goods pageParams4Goods, String goodsSn);

    List<Goods> listPageGoodsByName(PageParams4Goods pageParams4Goods, String name);

    List<Goods> listPageGoodsByGoodsSnAndName(PageParams4Goods pageParams4Goods, String goodsSn, String name);

    Goods getSingleGoodsById(int id);

    int deleteSingleGoodsById(Integer goodsId);
}
