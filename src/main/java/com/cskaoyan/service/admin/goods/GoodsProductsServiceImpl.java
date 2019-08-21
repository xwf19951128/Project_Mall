package com.cskaoyan.service.admin.goods;

import com.cskaoyan.bean.admin.goods.GoodsProduct;
import com.cskaoyan.bean.admin.goods.GoodsSpecification;
import com.cskaoyan.mapper.goods.GoodsProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsProductsServiceImpl implements GoodsProductService {

    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Override
    public List<GoodsProduct> listGoodsProductsByGoodsId(int id) {
        return goodsProductMapper.listGoodsProductsByGoodsId(id);
    }

    @Override
    public int insertGoodsProduct(List<Map<String, Object>> goodsProductMapList, Integer lastInsertGoodsId) {
        for (Map<String, Object> goodsProductMap : goodsProductMapList) {
            List<GoodsSpecification> goodsSpecificationList = (List<GoodsSpecification>) goodsProductMap.get("specifications");
            String[] goodsSpecificationArray = goodsSpecificationList.toArray(new String[goodsProductMapList.size()]);
            goodsProductMap.put("specifications", goodsSpecificationArray);
        }
        HashMap<String, Object> otherAttributeMap = new HashMap<>();
        otherAttributeMap.put("addTime", new Date());
        otherAttributeMap.put("updateTime", new Date());
        otherAttributeMap.put("deleted", 0);
        return goodsProductMapper.insertGoodsProduct(goodsProductMapList, lastInsertGoodsId, otherAttributeMap);
    }
}
