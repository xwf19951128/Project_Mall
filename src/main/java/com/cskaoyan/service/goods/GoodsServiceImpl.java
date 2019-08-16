package com.cskaoyan.service.goods;

import com.cskaoyan.bean.goods.Goods;
import com.cskaoyan.bean.goods.GoodsExample;
import com.cskaoyan.bean.goods.PageParams4Goods;
import com.cskaoyan.mapper.goods.GoodsMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public long countTotalGoodsCount() {
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdIsNotNull();
        return goodsMapper.countByExample(goodsExample);
    }

    @Override
    public List<Goods> listPageGoods(PageParams4Goods pageParams4Goods) {
        GoodsExample goodsExample = new GoodsExample();
        PageHelper.startPage(pageParams4Goods.getPage(), pageParams4Goods.getLimit());
        goodsExample.setOrderByClause(pageParams4Goods.getSort() + " " + pageParams4Goods.getOrder());
        goodsExample.createCriteria().andIdIsNotNull();
        return goodsMapper.selectByExample(goodsExample);
    }
}
