package com.cskaoyan.service.admin.statistic;

import com.cskaoyan.bean.admin.statistic.StatGoods;
import com.cskaoyan.bean.admin.statistic.StatOrder;
import com.cskaoyan.bean.admin.statistic.StatUser;
import com.cskaoyan.mapper.statistic.StatisticMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StatServiceImpl implements StatService {

    @Autowired
    StatisticMapper statisticMapper;

    @Override
    public List<StatUser> statUser() {
        List<StatUser> statUsers = statisticMapper.statUser();
        return statUsers;
    }

    @Override
    public List<StatOrder> statOrder() {
        List<StatOrder> statOrders = statisticMapper.statOrder();
        return statOrders;
    }

    @Override
    public List<StatGoods> statGoods() {
        List<StatGoods> statGoods = statisticMapper.statGoods();
        return statGoods;
    }
}
