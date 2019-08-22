package com.cskaoyan.mapper.userManage;

import com.cskaoyan.bean.admin.mall.region.Region;
import com.cskaoyan.bean.admin.userManage.Address;
import com.cskaoyan.bean.wx.tools.Address_wx;
import com.cskaoyan.bean.wx.tools.Region_wx;
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

    // 根据数据库中address的id查询地址详情
    Address queryDetailAddressById(@Param("id") int id);

    // 查询区域
    List<Region_wx> queryRegionByPid(@Param("pid") int pid);

    // 插入地址
    int insertAddress(@Param("address") Address address);

    // 查找省
    Region_wx queryRegionByProvinceId(@Param("provinceId") int provinceId);

    // 查找市
    Region_wx queryRegionByCityId(@Param("cityId") int cityId);

    // 查找区
    Region_wx queryRegionByAreaId(@Param("areaId") int areaId);

    // 删除地址
    void deleteAddress(@Param("id") int id);

    // 更新地址
    int updateAddress(@Param("address") Address address);
}
