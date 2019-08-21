package com.cskaoyan.service.admin.userManage.impl;

import com.cskaoyan.bean.admin.userManage.Address;
import com.cskaoyan.mapper.userManage.ShoppingAddressMapper;
import com.cskaoyan.service.admin.userManage.ShoppingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingAddressServiceImpl implements ShoppingAddressService {

    @Autowired
    ShoppingAddressMapper shoppingAddressMapper;

    @Override
    public List<Address> queryShoppingAddress() {
        return shoppingAddressMapper.queryShoppingAddress();
    }

    @Override
    public List<Address> queryAddressByUsername(String name) {
        return shoppingAddressMapper.queryAddressByUsername(name);
    }

    @Override
    public List<Address> queryAddressByUserId(String userId) {
        return shoppingAddressMapper.queryAddressByUserId(userId);
    }

    @Override
    public List<Address> queryAddressByUsernameAndUserId(String name, String userId) {
        return shoppingAddressMapper.queryAddressByUsernameAndUserId(name, userId);
    }
}
