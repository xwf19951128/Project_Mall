package com.cskaoyan.service.admin.goods;

import com.cskaoyan.bean.admin.goods.GoodsProduct;

import java.util.List;

public interface GoodsProductService {
    List<GoodsProduct> listGoodsProductsByGoodsId(int id);
}
