package com.cskaoyan.service.statistic;

import com.cskaoyan.bean.statistic.ConfigMall;
import com.cskaoyan.bean.statistic.LiteMall;

import java.util.List;

public interface ConfigService {
    //在系统配置表中，查询和商城配置相关的几条记录
    public List<LiteMall> queryConfigMall();

    //在系统配置表中，查询和运费配置相关的几条记录
    public List<LiteMall> queryConfigExpress();

    //在系统配置表中，查询和订单配置相关的几条记录
    public List<LiteMall> queryConfigOrder();

    //在系统配置表中，查询和微信小程序配置相关的几条记录
    public List<LiteMall> queryConfigWx();

}
