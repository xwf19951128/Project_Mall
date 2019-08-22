package com.cskaoyan.mapper.userManage;

import com.cskaoyan.bean.wx.order.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper_wx {
    List<OrderVo> queryUnpayOrder(@Param("showType") int showType, @Param("userId") int userId);
}
