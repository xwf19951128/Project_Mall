package com.cskaoyan.service.wx.tools.impl;


import com.cskaoyan.bean.admin.userManage.Address;
import com.cskaoyan.bean.admin.userManage.FeedBack;
import com.cskaoyan.bean.wx.tools.Address_wx;
import com.cskaoyan.bean.wx.tools.DetailedAddress_wx;
import com.cskaoyan.bean.wx.tools.Region_wx;
import com.cskaoyan.mapper.userManage.FeedBackMapper;
import com.cskaoyan.mapper.userManage.ShoppingAddressMapper;
import com.cskaoyan.service.wx.tools.AddressService_wx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AddressServiceImpl_wx implements AddressService_wx {

    @Autowired
    ShoppingAddressMapper shoppingAddressMapper;
    @Autowired
    FeedBackMapper feedBackMapper;

    /**
     * 查询当前登录的用户的所有地址
     * @param userId 当前用户的id
     * @return 查询到的地址集合
     */
    @Override
    public List<Address_wx> queryAddress_wxByUserId(int userId) {
        // 创建一个微信端的地址集合
        List<Address_wx> address_wxes = new ArrayList<>();

        // 一开始把new Address_wx()写在外面，
        // 导致address_wxes.add(address_wx); 每次都是加相同的引用
        // 所以所有的数据就和最后一个address_wx一致。
        // Address_wx address_wx = new Address_wx();

        // 从数据库中，根据用户id查询地址
        List<Address> addresses = shoppingAddressMapper.queryAddressByUserId(userId + "");

        // 遍历查询到的地址，然后把它赋值给微信端的地址JavaBean
        for (Address address : addresses) {
            // 这个address_wx一定要放在里面，这样每次new出来的address_wx才有新的地址
            Address_wx address_wx = new Address_wx();
            address_wx.setId(address.getId());
            address_wx.setName(address.getName());
            address_wx.setMobile(address.getMobile());

            /*if (address.getIsDefault() == 0) {
                address_wx.setIsDefault(false);
            } else {
                address_wx.setIsDefault(true);
            }*/

            // 虽然前端抓包isDefault是true和false，
            // 但传int类型过去就可以，其他交给前端处理。

            address_wx.setIsDefault(address.getIsDefault());

            String detailedAddress = address.getProvince() + address.getCity() + address.getArea() + address.getAddress();
            address_wx.setDetailedAddress(detailedAddress);

            address_wxes.add(address_wx);
        }

        return address_wxes;
    }

    /**
     * 查询地址详情
     * @param id 这个id对应数据库address表中的id
     * @return 返回详细地址的JavaBean
     */
    @Override
    public DetailedAddress_wx queryDetailAddressById(int id) {
        DetailedAddress_wx detailedAddress_wx = new DetailedAddress_wx();
        Address address = shoppingAddressMapper.queryDetailAddressById(id);

        if (address == null) {
            return null;
        } else {
            detailedAddress_wx.setAddress(address.getAddress());
            detailedAddress_wx.setAreaId(address.getAreaId());
            detailedAddress_wx.setAreaName(address.getArea());
            detailedAddress_wx.setCityId(address.getCityId());
            detailedAddress_wx.setCityName(address.getCity());
            detailedAddress_wx.setId(address.getId());
            detailedAddress_wx.setIsDefault(address.getIsDefault());
            detailedAddress_wx.setMobile(address.getMobile());
            detailedAddress_wx.setName(address.getName());
            detailedAddress_wx.setProvinceId(address.getProvinceId());
            detailedAddress_wx.setProvinceName(address.getProvince());
        }

        return detailedAddress_wx;

    }

    /**
     * 查询区域
     * @param pid pid是父区域的id，详情看数据库说明
     * @return 返回区域的集合
     */
    @Override
    public List<Region_wx> queryRegionByPid(int pid) {
        return shoppingAddressMapper.queryRegionByPid(pid);
    }

    /**
     * 插入地址
     * @param hashMap 从前端接收到的map
     * @param userId 当前登录的账户
     * @return 返回整数>0说明插入成功
     */
    @Override
    public int insertAddress(HashMap<String, Object> hashMap, int userId) {
        Address address = new Address();
        // 封装前端上传的数据
        address.setAddress((String) hashMap.get("address"));
        address.setId((int) hashMap.get("id"));

        // 这里卡了特别久。
        // 前端如果事默认地址返回true，非默认返回0，一个是布尔类型，一个是int类型
        // 所以用getClass().getName()来区分是什么类型
        if (hashMap.get("isDefault").getClass().getName() == "java.lang.Boolean") {
            address.setIsDefault(1);
        }
        if (hashMap.get("isDefault").getClass().getName() == "java.lang.Integer") {
            address.setIsDefault(0);
        }

        address.setMobile((String) hashMap.get("mobile"));
        address.setName((String) hashMap.get("name"));

        // 封装当前用户登录的id
        address.setUserId(userId);

        // 封装省、市、区
        Region_wx province = shoppingAddressMapper.queryRegionByProvinceId((int) hashMap.get("provinceId"));
        address.setProvinceId(province.getId());
        address.setProvince(province.getName());
        Region_wx city = shoppingAddressMapper.queryRegionByCityId((int) hashMap.get("cityId"));
        address.setCityId(city.getId());
        address.setCity(city.getName());
        Region_wx area = shoppingAddressMapper.queryRegionByAreaId((int) hashMap.get("areaId"));
        address.setAreaId(area.getId());
        address.setArea(area.getName());

        return shoppingAddressMapper.insertAddress(address);
    }

    /**
     * 删除地址
     * @param id 对应address表中的id
     */
    @Override
    public void deleteAddress(@RequestBody int id) {
        shoppingAddressMapper.deleteAddress(id);
    }

    /**
     * 编辑地址
     * @param hashMap 从前端接收到的map
     * @param userId 当前登录的账户
     * @return 返回整数>0说明编辑成功
     */
    @Override
    public int updateAddress(HashMap<String, Object> hashMap, int userId) {
        Address address = new Address();
        // 封装前端上传的数据
        address.setAddress((String) hashMap.get("address"));
        address.setId((int) hashMap.get("id"));

        // 这里卡了特别久。
        // 前端如果事默认地址返回true，非默认返回0，一个是布尔类型，一个是int类型
        // 所以用getClass().getName()来区分是什么类型
        if (hashMap.get("isDefault").getClass().getName() == "java.lang.Boolean") {
            address.setIsDefault(1);
        }
        if (hashMap.get("isDefault").getClass().getName() == "java.lang.Integer") {
            address.setIsDefault(0);
        }

        address.setMobile((String) hashMap.get("mobile"));
        address.setName((String) hashMap.get("name"));

        // 封装当前用户登录的id
        address.setUserId(userId);

        // 封装省、市、区
        Region_wx province = shoppingAddressMapper.queryRegionByProvinceId((int) hashMap.get("provinceId"));
        address.setProvinceId(province.getId());
        address.setProvince(province.getName());
        Region_wx city = shoppingAddressMapper.queryRegionByCityId((int) hashMap.get("cityId"));
        address.setCityId(city.getId());
        address.setCity(city.getName());
        Region_wx area = shoppingAddressMapper.queryRegionByAreaId((int) hashMap.get("areaId"));
        address.setAreaId(area.getId());
        address.setArea(area.getName());

        return shoppingAddressMapper.updateAddress(address);
    }

    @Override
    public void insertFeedBack(FeedBack feedBack) {
       feedBackMapper.insertFeedBack(feedBack);
    }
}
