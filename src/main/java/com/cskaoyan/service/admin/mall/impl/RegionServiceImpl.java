package com.cskaoyan.service.admin.mall.impl;

import com.cskaoyan.bean.admin.mall.region.Province;
import com.cskaoyan.mapper.mall.RegionMapper;
import com.cskaoyan.service.admin.mall.RegionService;
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
