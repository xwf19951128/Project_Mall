package com.cskaoyan.service.admin.goods;

import com.cskaoyan.bean.admin.goods.GoodsAttribute;

import java.util.List;
import java.util.Map;

public interface GoodsAttributeService {
    List<GoodsAttribute> listGoodsAttributesByGoodsId(int id);

    int insertGoodsAttributes(List<GoodsAttribute> goodsAttributeList, Integer lastInsertGoodsId);

    int updateGoodsAttributes(List<Map<String, Object>> goodsAttributeList, Integer lastUpdateGoodsId);
}
