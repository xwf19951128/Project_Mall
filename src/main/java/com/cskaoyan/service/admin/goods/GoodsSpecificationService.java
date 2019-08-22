package com.cskaoyan.service.admin.goods;

import com.cskaoyan.bean.admin.goods.GoodsSpecification;

import java.util.List;
import java.util.Map;

public interface GoodsSpecificationService {

    List<GoodsSpecification> listGoodsSpecificationsByGoodsId(int id);

    int insertSpecifications(List<Map<String, Object>> goodsSpecificationList, Integer lastInsertGoodsId);

    int updateSpecifications(List<Map<String, Object>> goodsSpecificationMapList, Integer lastUpdateGoodsId);
}
