package com.cskaoyan.service.goods;

import com.cskaoyan.bean.goods.GoodsProduct;

import java.util.List;

public interface GoodsProductService {
    List<GoodsProduct> listGoodsProductsByGoodsId(int id);
}
