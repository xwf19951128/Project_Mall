package com.cskaoyan.service.admin.userManage.impl;

import com.cskaoyan.bean.admin.userManage.VipCollect;
import com.cskaoyan.mapper.userManage.VipCollectMapper;
import com.cskaoyan.service.admin.userManage.VipCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VipCollectServiceImpl implements VipCollectService {

    // 从容器中取出mapper接口
    @Autowired
    VipCollectMapper vipCollectMapper;

    // 查询所有会员收藏
    @Override
    public List<VipCollect> queryVipCollect() {
        return vipCollectMapper.queryVipCollect();
    }

    @Override
    public List<VipCollect> queryVipCollectByValueId(String valueId) {
        return vipCollectMapper.queryVipCollectByValueId(valueId);
    }

    @Override
    public List<VipCollect> queryVipCollectByUserId(String userId) {
        return vipCollectMapper.queryVipCollectByUserId(userId);
    }

    @Override
    public List<VipCollect> queryVipCollectByUserIdAndValueId(String userId, String valueId) {
        return vipCollectMapper.queryVipCollectByUserIdAndValueId(userId, valueId);
    }
}
