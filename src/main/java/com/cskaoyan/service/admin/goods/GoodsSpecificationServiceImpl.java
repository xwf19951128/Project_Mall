package com.cskaoyan.service.admin.goods;

import com.cskaoyan.bean.admin.goods.GoodsSpecification;
import com.cskaoyan.bean.admin.goods.GoodsSpecificationExample;
import com.cskaoyan.mapper.goods.GoodsSpecificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GoodsSpecificationServiceImpl implements GoodsSpecificationService {

    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Override
    public List<GoodsSpecification> listGoodsSpecificationsByGoodsId(int id) {
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        goodsSpecificationExample.createCriteria().andGoodsIdEqualTo(id);
        return goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
    }

    @Override
    public int insertSpecifications(List<Map<String, Object>> goodsSpecificationMapList, Integer lastInsertGoodsId) {
        for (Map<String, Object> goodsSpecificationMap : goodsSpecificationMapList) {
            goodsSpecificationMap.put("goodsId", lastInsertGoodsId);
            goodsSpecificationMap.put("addTime", new Date());
            goodsSpecificationMap.put("deleted", 0);
        }
        return goodsSpecificationMapper.insertSpecifications(goodsSpecificationMapList);
    }

    @Override
    public int updateSpecifications(List<Map<String, Object>> goodsSpecificationMapList, Integer lastUpdateGoodsId) {
        for (Map<String, Object> goodsSpecificationMap : goodsSpecificationMapList) {
            goodsSpecificationMap.put("goodsId", lastUpdateGoodsId);
            goodsSpecificationMap.put("updateTime", new Date());
            goodsSpecificationMap.put("deleted", 0);
        }
        return goodsSpecificationMapper.updateSpecifications(goodsSpecificationMapList);
    }
}
