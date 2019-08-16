package com.cskaoyan.mapper.spread;

import com.cskaoyan.bean.spread.MallCoupon;
import com.cskaoyan.bean.spread.MallCouponExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallCouponMapper {
    long countByExample(MallCouponExample example);

    int deleteByExample(MallCouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallCoupon record);

    int insertSelective(MallCoupon record);

    List<MallCoupon> selectByExample(MallCouponExample example);

    MallCoupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallCoupon record, @Param("example") MallCouponExample example);

    int updateByExample(@Param("record") MallCoupon record, @Param("example") MallCouponExample example);

    int updateByPrimaryKeySelective(MallCoupon record);

    int updateByPrimaryKey(MallCoupon record);
}