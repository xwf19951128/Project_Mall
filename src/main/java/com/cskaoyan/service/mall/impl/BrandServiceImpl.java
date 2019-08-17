package com.cskaoyan.service.mall.impl;

import com.cskaoyan.bean.mall.brand.Brand;
import com.cskaoyan.mapper.mall.BrandMapper;
import com.cskaoyan.service.mall.BrandService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandMapper brandMapper;

    @Override
    public List<Brand> getBrandList() {
        return brandMapper.getBrandList();
    }

    @Override
    public Brand updateBrandById(Brand brand) {
        int i = brandMapper.updateBrandById(brand);
        if (i > 0) {
            return brandMapper.queryBrandById(brand.getId());
        }
        return null;
    }

    @Override
    public Brand insertBrand(Brand brand) {
        int i = brandMapper.insert(brand);
        if (i > 0) {
            return brandMapper.queryBrandByAddTime(brand.getAddTime());
        }
        return null;
    }
}
