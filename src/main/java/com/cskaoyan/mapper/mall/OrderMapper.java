package com.cskaoyan.mapper.mall;

import com.cskaoyan.bean.admin.mall.order.GoodsDetail;
import com.cskaoyan.bean.admin.mall.order.Order;
import com.cskaoyan.bean.admin.mall.order.OrderExample;
import com.cskaoyan.bean.admin.userManage.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);


    List<Order> getOrderListBy(@Param("orderStatus") Short orderStatus, @Param("orderSn") String orderSn, @Param("id") Integer userId);

    List<Order> getOrderList();

    List<GoodsDetail> getOrderGoodsById(@Param("id") int id);

    User getOrderUserById(int id);
}