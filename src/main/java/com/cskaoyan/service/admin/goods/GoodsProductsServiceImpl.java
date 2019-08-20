package com.cskaoyan.service.admin.goods;

import com.cskaoyan.bean.admin.goods.GoodsProduct;
import com.cskaoyan.mapper.goods.GoodsProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsProductsServiceImpl implements GoodsProductService {

    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Override
    public List<GoodsProduct> listGoodsProductsByGoodsId(int id) {
        return goodsProductMapper.listGoodsProductsByGoodsId(id);
    }
}
