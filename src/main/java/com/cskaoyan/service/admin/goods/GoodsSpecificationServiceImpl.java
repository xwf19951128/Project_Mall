package com.cskaoyan.service.admin.goods;

import com.cskaoyan.bean.admin.goods.GoodsSpecification;
import com.cskaoyan.bean.admin.goods.GoodsSpecificationExample;
import com.cskaoyan.mapper.goods.GoodsSpecificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsSpecificationServiceImpl implements GoodsSpecificationService{

    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Override
    public List<GoodsSpecification> listGoodsSpecificationsByGoodsId(int id) {
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        goodsSpecificationExample.createCriteria().andGoodsIdEqualTo(id);
        return goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
    }
}
