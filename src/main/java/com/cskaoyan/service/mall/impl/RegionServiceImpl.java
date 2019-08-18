package com.cskaoyan.service.mall.impl;

import com.cskaoyan.bean.mall.region.Province;
import com.cskaoyan.mapper.mall.RegionMapper;
import com.cskaoyan.service.mall.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    RegionMapper regionMapper;

    @Override
    public List<Province> getRegion() {
        List<Province> list = regionMapper.getProvince();
        System.out.println(list);
        return list;
    }
}
