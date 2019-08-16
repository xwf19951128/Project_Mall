package com.cskaoyan.mapper.spread;

import com.cskaoyan.bean.spread.UserCoupon;
import com.cskaoyan.bean.spread.UserCouponExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserCouponMapper {
    long countByExample(UserCouponExample example);

    int deleteByExample(UserCouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserCoupon record);

    int insertSelective(UserCoupon record);

    List<UserCoupon> selectByExample(UserCouponExample example);

    UserCoupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserCoupon record, @Param("example") UserCouponExample example);

    int updateByExample(@Param("record") UserCoupon record, @Param("example") UserCouponExample example);

    int updateByPrimaryKeySelective(UserCoupon record);

    int updateByPrimaryKey(UserCoupon record);
}