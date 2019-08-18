package com.cskaoyan.mapper.userManage;

import com.cskaoyan.bean.userManage.FootMark;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FootMarkMapper {

    // 查询所有会员足迹
    List<FootMark> queryFootMark();

    // 通过商品id进行查询
    List<FootMark> queryFootMarkByGoodsId(@Param("goodsId") String goodsId);

    // 通过用户id进行查询
    List<FootMark> queryFootMarkByUserId(@Param("userId") String userId);

    // 通过用户id和商品id进行查询
    List<FootMark> queryFootMarkByUserIdAndGoodsId(@Param("userId") String userId, @Param("goodsId") String goodsId);
}
