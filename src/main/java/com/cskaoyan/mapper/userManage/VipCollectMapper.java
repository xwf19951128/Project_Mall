package com.cskaoyan.mapper.userManage;

import com.cskaoyan.bean.admin.userManage.VipCollect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VipCollectMapper {

    // 查询所有会员收藏
    List<VipCollect> queryVipCollect();

    // 通过商品id进行查询
    List<VipCollect> queryVipCollectByValueId(@Param("valueId") String valueId);

    // 通过用户id进行查询
    List<VipCollect> queryVipCollectByUserId(@Param("userId") String userId);

    // 通过用户id和商品id进行查询
    List<VipCollect> queryVipCollectByUserIdAndValueId(@Param("userId") String userId, @Param("valueId") String valueId);
}
