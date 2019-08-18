package com.cskaoyan.service.goods;

import com.cskaoyan.bean.goods.Goods;
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
/*        GoodsExample goodsExample = new GoodsExample();
        goodsExample.createCriteria().andIdIsNotNull();
        return goodsMapper.countByExample(goodsExample);*/
        return goodsMapper.countTotalGoodsCount();
    }

    @Override
    public List<Goods> listPageGoods(PageParams4Goods pageParams4Goods) {
/*        GoodsExample goodsExample = new GoodsExample();
        PageHelper.startPage(pageParams4Goods.getPage(), pageParams4Goods.getLimit());
        goodsExample.setOrderByClause(pageParams4Goods.getSort() + " " + pageParams4Goods.getOrder());
        goodsExample.createCriteria().andIdIsNotNull();
        return goodsMapper.selectByExample(goodsExample);*/

//        int limit = pageParams4Goods.getLimit();
//        int offset = (pageParams4Goods.getPage() - 1) * limit;
        String sort = pageParams4Goods.getSort();
        String order = pageParams4Goods.getOrder();
        PageHelper.startPage(pageParams4Goods.getPage(), pageParams4Goods.getLimit());
        return goodsMapper.listAllGoods(sort, order);

    }

    @Override
    public List<Goods> listPageGoodsByGoodsSn(PageParams4Goods pageParams4Goods, String goodsSn) {
        String sort = pageParams4Goods.getSort();
        String order = pageParams4Goods.getOrder();
        String like = "%" + goodsSn + "%";
        PageHelper.startPage(pageParams4Goods.getPage(), pageParams4Goods.getLimit());
        return goodsMapper.listSearchGoodsByGoodsSn(sort, order, like);
    }

    @Override
    public List<Goods> listPageGoodsByName(PageParams4Goods pageParams4Goods, String name) {
        String sort = pageParams4Goods.getSort();
        String order = pageParams4Goods.getOrder();
        String like = "%" + name + "%";
        PageHelper.startPage(pageParams4Goods.getPage(), pageParams4Goods.getLimit());
        return goodsMapper.listSearchGoodsByName(sort, order, like);
    }

    @Override
    public List<Goods> listPageGoodsByGoodsSnAndName(PageParams4Goods pageParams4Goods, String goodsSn, String name) {
        String sort = pageParams4Goods.getSort();
        String order = pageParams4Goods.getOrder();
        String like1 = "%" + goodsSn + "%";
        String like2 = "%" + name + "%";
        PageHelper.startPage(pageParams4Goods.getPage(), pageParams4Goods.getLimit());
        return goodsMapper.listSearchGoodsByGoodsSnAndName(sort, order, like1, like2);
    }
}
