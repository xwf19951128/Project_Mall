package com.cskaoyan.service.wx.tools;

import com.cskaoyan.bean.admin.userManage.FeedBack;
import com.cskaoyan.bean.wx.tools.Address_wx;
import com.cskaoyan.bean.wx.tools.DetailedAddress_wx;
import com.cskaoyan.bean.wx.tools.Region_wx;

import java.util.HashMap;
import java.util.List;

public interface AddressService_wx {
    // 通过当前用户名id获取全部地址
    List<Address_wx> queryAddress_wxByUserId(int userId);

    // 查询地址详情
    DetailedAddress_wx queryDetailAddressById(int id);

    // 查询区域
    List<Region_wx> queryRegionByPid(int pid);

    // 插入地址
    int insertAddress(HashMap<String, Object> hashMap, int userId);

    // 删除地址
    void deleteAddress(int id);

    // 编辑地址
    int updateAddress(HashMap<String, Object> hashMap, int userId);

    //新增一条用户反馈
    void insertFeedBack(FeedBack feedBack);
}
