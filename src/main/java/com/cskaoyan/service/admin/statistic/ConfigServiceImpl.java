package com.cskaoyan.service.admin.statistic;

import com.cskaoyan.bean.admin.statistic.LiteMall;

import com.cskaoyan.bean.admin.statistic.LiteMallExample;
import com.cskaoyan.mapper.statistic.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    ConfigMapper configMapper;


    @Override
    public List<LiteMall> queryConfigMall() {
        LiteMallExample mallExample = new LiteMallExample();
        LiteMallExample.Criteria criteria = mallExample.createCriteria();
        criteria.andKeyNameLike("%"+"cskaoyan_mall_mall_"+"%");
        List<LiteMall> liteMallList = configMapper.selectByExample(mallExample);
        return liteMallList;
    }

    @Override
    public List<LiteMall> queryConfigExpress() {
        LiteMallExample expressExample = new LiteMallExample();
        LiteMallExample.Criteria criteria = expressExample.createCriteria();
        criteria.andKeyNameLike("%cskaoyan_mall_express_%");
        List<LiteMall> liteMallList = configMapper.selectByExample(expressExample);
        return liteMallList;
    }

    @Override
    public List<LiteMall> queryConfigOrder() {
        LiteMallExample orderExample = new LiteMallExample();
        LiteMallExample.Criteria criteria = orderExample.createCriteria();
        criteria.andKeyNameLike("%cskaoyan_mall_order_%");
        List<LiteMall> liteMallList = configMapper.selectByExample(orderExample);
        return liteMallList;
    }

    @Override
    public List<LiteMall> queryConfigWx() {
        LiteMallExample wxExample = new LiteMallExample();
        LiteMallExample.Criteria criteria = wxExample.createCriteria();
        criteria.andKeyNameLike("%cskaoyan_mall_wx_%");
        List<LiteMall> liteMallList = configMapper.selectByExample(wxExample);
        return liteMallList;
    }
}
