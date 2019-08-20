package com.cskaoyan.service.admin.goods;

import com.cskaoyan.bean.admin.goods.GoodsAttribute;

import java.util.List;

public interface GoodsAttributeService {
    List<GoodsAttribute> listGoodsAttributesByGoodsId(int id);
}
