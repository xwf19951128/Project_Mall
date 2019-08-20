package com.cskaoyan.service.admin.userManage;

import com.cskaoyan.bean.admin.userManage.FootMark;

import java.util.List;

public interface FootMarkService {
    // 查询所有会员足迹
    List<FootMark> queryFootMark();

    // 通过商品id进行查询
    List<FootMark> queryFootMarkByGoodsId(String goodsId);

    // 通过用户id进行查询
    List<FootMark> queryFootMarkByUserId(String userId);

    // 通过用户id和商品id进行查询
    List<FootMark> queryFootMarkByUserIdAndGoodsId(String userId, String goodsId);
}
