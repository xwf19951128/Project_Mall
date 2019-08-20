package com.cskaoyan.service.admin.statistic;

import com.cskaoyan.bean.admin.statistic.StatGoods;
import com.cskaoyan.bean.admin.statistic.StatOrder;
import com.cskaoyan.bean.admin.statistic.StatUser;

import java.util.List;

public interface StatService {
    //统计用户
    public List<StatUser> statUser();
    //统计订单
    public List<StatOrder> statOrder();
    //统计商品
    public List<StatGoods> statGoods();
}
