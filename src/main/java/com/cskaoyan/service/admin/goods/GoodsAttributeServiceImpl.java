package com.cskaoyan.service.admin.goods;

import com.cskaoyan.bean.admin.goods.GoodsAttribute;
import com.cskaoyan.bean.admin.goods.GoodsAttributeExample;
import com.cskaoyan.mapper.goods.GoodsAttributeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsAttributeServiceImpl implements GoodsAttributeService {

    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;

    @Override
    public List<GoodsAttribute> listGoodsAttributesByGoodsId(int id) {
        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        goodsAttributeExample.createCriteria().andGoodsIdEqualTo(id);
        return goodsAttributeMapper.selectByExample(goodsAttributeExample);
    }
}
