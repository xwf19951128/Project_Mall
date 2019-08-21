package com.cskaoyan.service.admin.goods;

import com.cskaoyan.bean.admin.goods.GoodsAttribute;
import com.cskaoyan.bean.admin.goods.GoodsAttributeExample;
import com.cskaoyan.mapper.goods.GoodsAttributeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
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

    @Override
    public int insertGoodsAttributes(List<GoodsAttribute> goodsAttributeList, Integer lastInsertGoodsId) {
/*        for (GoodsAttribute goodsAttribute : goodsAttributeList) {
            int result = goodsAttributeMapper.insertGoodsAttributeByLastGoodsId(goodsAttribute, lastInsertGoodsId);
            if (result == 0){
                return result;
            }
        }*/
        HashMap<String, Object> otherAttributeMap = new HashMap<>();
        otherAttributeMap.put("addTime", new Date());
        otherAttributeMap.put("updateTime", new Date());
        otherAttributeMap.put("deleted", 0);
        return goodsAttributeMapper.insertGoodsAttributeByLastGoodsId(goodsAttributeList, lastInsertGoodsId, otherAttributeMap);
    }
}
