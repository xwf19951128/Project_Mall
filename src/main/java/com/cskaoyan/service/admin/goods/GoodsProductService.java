package com.cskaoyan.service.admin.goods;

import com.cskaoyan.bean.admin.goods.GoodsProduct;

import java.util.List;
import java.util.Map;

public interface GoodsProductService {

    List<GoodsProduct> listGoodsProductsByGoodsId(int id);

    int insertGoodsProduct(List<Map<String, Object>> goodsProductList, Integer lastInsertGoodsId);
}
