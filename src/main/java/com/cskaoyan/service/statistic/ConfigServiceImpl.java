package com.cskaoyan.service.statistic;

import com.cskaoyan.bean.statistic.ConfigMall;
import com.cskaoyan.mapper.statistic.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    ConfigMapper configMapper;

    @Override
    public ConfigMall queryConfigMall() {
        ConfigMall configMall = configMapper.queryConfigMall();
        return configMall;
    }
}
