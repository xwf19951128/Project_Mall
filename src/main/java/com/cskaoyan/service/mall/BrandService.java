package com.cskaoyan.service.mall;

import com.cskaoyan.bean.mall.brand.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getBrandList();

    Brand updateBrandById(Brand brand);

    Brand insertBrand(Brand brand);
}
