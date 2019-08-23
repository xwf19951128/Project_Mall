package com.cskaoyan.service.wx.index;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.mapper.goods.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 17:11 2019/8/22
 */
@Service
public class GoodListServiceImpl implements GoodListService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<Goods> selectAllCountGoods() {
        return goodsMapper.selectAllCountGoods();
    }
}
