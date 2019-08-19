package com.cskaoyan.service.goods;

import com.cskaoyan.bean.goods.GoodsAttribute;

import java.util.List;

public interface GoodsAttributeService {
    List<GoodsAttribute> listGoodsAttributesByGoodsId(int id);
}
