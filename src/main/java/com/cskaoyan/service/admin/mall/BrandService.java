package com.cskaoyan.service.admin.mall;

import com.cskaoyan.bean.admin.mall.brand.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getBrandList(Integer id, String name);

    Brand updateBrandById(Brand brand);

    void insertBrand(Brand brand);

    void deleteBrandById(Integer id);
}
