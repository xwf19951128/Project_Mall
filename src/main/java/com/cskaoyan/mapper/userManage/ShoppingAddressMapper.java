package com.cskaoyan.mapper.userManage;

import com.cskaoyan.bean.admin.userManage.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingAddressMapper {
    // 查询所有用户的收获地址
    List<Address> queryShoppingAddress();

    // 根据收货人名称查询
    List<Address> queryAddressByUsername(@Param("name") String name);

    // 根据用户id查询
    List<Address> queryAddressByUserId(@Param("userId") String userId);

    // 根据用户id和收货人姓名查询
    List<Address> queryAddressByUsernameAndUserId(@Param("name") String name, @Param("userId") String userId);
}
