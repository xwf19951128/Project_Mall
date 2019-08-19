package com.cskaoyan.service.goods;

import com.cskaoyan.bean.goods.GoodsSpecification;

import java.util.List;

public interface GoodsSpecificationService {
    List<GoodsSpecification> listGoodsSpecificationsByGoodsId(int id);
}
