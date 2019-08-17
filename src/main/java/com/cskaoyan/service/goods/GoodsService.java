package com.cskaoyan.service.goods;

import com.cskaoyan.bean.goods.Goods;
import com.cskaoyan.bean.goods.PageParams4Goods;

import java.util.List;

public interface GoodsService {

    long countTotalGoodsCount();

    List<Goods> listPageGoods(PageParams4Goods pageParams4Goods, String goodsSn, String name);
}
