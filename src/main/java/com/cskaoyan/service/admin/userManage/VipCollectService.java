package com.cskaoyan.service.admin.userManage;

import com.cskaoyan.bean.admin.userManage.VipCollect;

import java.util.List;

public interface VipCollectService {
    // 查询所有会员收藏
    List<VipCollect> queryVipCollect();

    // 通过商品id进行查询
    List<VipCollect> queryVipCollectByValueId(String valueId);

    // 通过用户id进行查询
    List<VipCollect> queryVipCollectByUserId(String userId);

    // 通过用户id和商品id进行查询
    List<VipCollect> queryVipCollectByUserIdAndValueId(String userId, String valueId);
}
