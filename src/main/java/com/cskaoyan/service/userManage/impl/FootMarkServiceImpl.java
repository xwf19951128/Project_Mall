package com.cskaoyan.service.userManage.impl;

import com.cskaoyan.bean.userManage.FootMark;
import com.cskaoyan.mapper.userManage.FootMarkMapper;
import com.cskaoyan.service.userManage.FootMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FootMarkServiceImpl implements FootMarkService {

    @Autowired
    FootMarkMapper footMarkMapper;

    @Override
    public List<FootMark> queryFootMark() {
        return footMarkMapper.queryFootMark();
    }

    @Override
    public List<FootMark> queryFootMarkByGoodsId(String goodsId) {
        return footMarkMapper.queryFootMarkByGoodsId(goodsId);
    }

    @Override
    public List<FootMark> queryFootMarkByUserId(String userId) {
        return footMarkMapper.queryFootMarkByUserId(userId);
    }

    @Override
    public List<FootMark> queryFootMarkByUserIdAndGoodsId(String userId, String goodsId) {
        return footMarkMapper.queryFootMarkByUserIdAndGoodsId(userId, goodsId);
    }
}
