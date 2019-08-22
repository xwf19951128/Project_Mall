package com.cskaoyan.controller.wx.tools;

import com.cskaoyan.bean.wx.tools.Address_wx;
import com.cskaoyan.bean.wx.tools.DetailedAddress_wx;
import com.cskaoyan.bean.wx.tools.Region_wx;
import com.cskaoyan.service.wx.tools.AddressService_wx;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.cskaoyan.util.wx.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class AddressController_wx {

    @Autowired
    AddressService_wx addressService_wx;

    /**
     * 查询当前用户的购物地址
     * @param request 用来获取当前用户的id
     * @return 返回模板
     */
    @RequestMapping("/wx/address/list")
    @ResponseBody
    ResponseVo queryAddress(HttpServletRequest request) {
        // 获得请求头
        String tokenKey = request.getHeader("X-Litemall-Token");
        // 通过请求头获取userId
        int userId = UserTokenManager.getUserId(tokenKey).intValue();

        // 通过当前用户名id获取全部地址
        List<Address_wx> address_wxes = addressService_wx.queryAddress_wxByUserId(userId);

        return ResponseUtil.success(address_wxes);
    }

    /**
     * 查询地址详情
     * @param id 对应数据库中address的id
     * @return 用工具类返回Response模板
     */
    @RequestMapping("/wx/address/detail")
    @ResponseBody
    ResponseVo queryDetailAddress(int id) {

        DetailedAddress_wx detailedAddress_wx = addressService_wx.queryDetailAddressById(id);

        return ResponseUtil.success(detailedAddress_wx);
    }


    /**
     * 查询区域
     * @param pid 行政区域父id，例如区县的pid指向市，市的pid指向省，省的pid则是0
     * @return 返回response模板
     */
    @RequestMapping("/wx/region/list")
    @ResponseBody
    ResponseVo queryRegion(int pid) {
        List<Region_wx> regions = addressService_wx.queryRegionByPid(pid);
        return ResponseUtil.success(regions);
    }

    /**
     * 插入地址，如果已经存在就编辑
     * @param hashMap 用map接收前端的json形式
     * @param request 用来获取当前登录的用户
     * @return 返回模板
     */
    @RequestMapping("/wx/address/save")
    @ResponseBody
    ResponseVo saveAddress(@RequestBody HashMap<String, Object> hashMap, HttpServletRequest request) {
        // 获得请求头
        String tokenKey = request.getHeader("X-Litemall-Token");
        // 通过请求头获取userId
        int userId = UserTokenManager.getUserId(tokenKey).intValue();

        // 先查询一下，是否已经存在这个地址了
        DetailedAddress_wx detailedAddress_wx = addressService_wx.queryDetailAddressById((int) hashMap.get("id"));

        // 如果查不到这个记录就插入，查到了就更新
        int data;
        if (detailedAddress_wx == null) {
            data = addressService_wx.insertAddress(hashMap, userId);
        } else {
            data = addressService_wx.updateAddress(hashMap, userId);
        }

        return ResponseUtil.success(data);
    }

    /**
     * 删除地址
     * 记住这里的返回值是Json（键值对）要用Javabean或者Map接收！！！
     * @param hashMap 这是address表的id
     * @return 模板返回
     */
    @RequestMapping("/wx/address/delete")
    @ResponseBody
    ResponseVo deleteAddress(@RequestBody HashMap<String, Object> hashMap) {
        addressService_wx.deleteAddress((int) hashMap.get("id"));
        return ResponseUtil.success();
    }

}
