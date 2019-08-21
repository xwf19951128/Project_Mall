package com.cskaoyan.service.admin.userManage;

import com.cskaoyan.bean.admin.userManage.Address;

import java.util.List;

public interface ShoppingAddressService {
    // 查询所有用户的收获地址
    List<Address> queryShoppingAddress();

    // 根据收货人名称查询
    List<Address> queryAddressByUsername(String name);

    // 根据用户id查询
    List<Address> queryAddressByUserId(String userId);

    // 根据用户id和收货人姓名查询
    List<Address> queryAddressByUsernameAndUserId(String name, String userId);
}
