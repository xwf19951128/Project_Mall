package com.cskaoyan.mapper.statistic;

import com.cskaoyan.bean.statistic.StatGoods;
import com.cskaoyan.bean.statistic.StatOrder;
import com.cskaoyan.bean.statistic.StatUser;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface StatisticMapper {
    //统计用户
    public List<StatUser> statUser();
    //统计订单
    public List<StatOrder> statOrder();
    //统计商品
    public List<StatGoods> statGoods();
}
