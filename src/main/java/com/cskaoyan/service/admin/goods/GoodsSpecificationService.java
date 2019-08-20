package com.cskaoyan.service.admin.goods;

import com.cskaoyan.bean.admin.goods.GoodsSpecification;

import java.util.List;

public interface GoodsSpecificationService {
    List<GoodsSpecification> listGoodsSpecificationsByGoodsId(int id);
}
