package com.cskaoyan.service.wx.index;

import com.cskaoyan.bean.admin.mall.brand.Brand;
import com.cskaoyan.mapper.mall.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 11:22 2019/8/22
 */
@Service
public class BrandDetailServiceImpl implements BrandDetailService {

    @Autowired
    BrandMapper brandMapper;

    @Override
    public Brand selectBrandById(int id) {
        return brandMapper.selectBrandById(id);
    }
}
