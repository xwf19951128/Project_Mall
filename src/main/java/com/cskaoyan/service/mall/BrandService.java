package com.cskaoyan.service.mall;

import com.cskaoyan.bean.mall.brand.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getBrandList(Integer id, String name);

    Brand updateBrandById(Brand brand);

    void insertBrand(Brand brand);

    void deleteBrandById(Integer id);
}
