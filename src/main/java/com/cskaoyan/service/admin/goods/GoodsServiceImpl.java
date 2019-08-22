package com.cskaoyan.service.admin.goods;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.goods.PageParams4Goods;
import com.cskaoyan.mapper.goods.GoodsMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        PageHelper.startPage(pageParams4Goods.getPage(), pageParams4Goods.getLimit(), pageParams4Goods.getSort() + " " + pageParams4Goods.getOrder());
        return goodsMapper.listAllGoods();
        //        return goodsMapper.listAllGoods(sort, order);

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

    @Override
    public Goods getSingleGoodsById(int id) {
        return goodsMapper.getSingleGoodsById(id);
    }

    @Override
    public int deleteSingleGoodsById(Integer goodsId) {
        return goodsMapper.deleteSingleGoodsById(goodsId);
    }

    @Override
    public int insertSingleGoods(Map<String, Object> goodsMap) {
        List<String> galleryList = (ArrayList<String>)goodsMap.get("gallery");
        String[] galleryArray = galleryList.toArray(new String[galleryList.size()]);
//        goodsMap.put("id", 0);
        goodsMap.put("gallery", galleryArray);
        goodsMap.put("sortOrder", 123);
        goodsMap.put("shareUrl", "");
        goodsMap.put("addTime", new Date());
        goodsMap.put("deleted", 0);
        int result = goodsMapper.insertSingleGoods(goodsMap);
        Integer lastInsertGoodsId = (Integer)goodsMap.get("id");
//        System.out.println("lastInsertGoodsId = " + lastInsertGoodsId);
//        return lastInsertGoodsId;
        return result;
    }

    @Override
    public int updateSingleGoods(Map<String, Object> goodsMap) {
        List<String> galleryList = (List<String>) goodsMap.get("gallery");
        String[] galleryArray = galleryList.toArray(new String[galleryList.size()]);
        goodsMap.put("gallery", galleryArray);
        goodsMap.put("sortOrder", 123);
        goodsMap.put("shareUrl", "");
        goodsMap.put("updateTime", new Date());
        goodsMap.put("deleted", 0);
        return goodsMapper.updateSingleGoods(goodsMap);
    }
}
