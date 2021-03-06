package com.cskaoyan.service.admin.statistic;

import com.cskaoyan.bean.admin.statistic.LiteMall;

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
    //根据keyname更新对应的keyValue
    void updateKeyValueByName(String key, String value);
}
