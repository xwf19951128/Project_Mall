package com.cskaoyan.mapper.coreservice;


import com.cskaoyan.bean.admin.spread.UserCouponExample;
import java.util.List;

import com.cskaoyan.bean.wx.coreservice.UserCoupon;
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

    List<UserCoupon> queryUserCouponList(@Param("username") String username, @Param("status") short status);

    long countByCid(@Param("id") Integer id);

    int updateStatus(List<UserCoupon> newlist);
}