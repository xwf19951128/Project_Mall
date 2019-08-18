package com.cskaoyan.controller.userManage;

import com.cskaoyan.bean.userManage.Address;
import com.cskaoyan.bean.userManage.DataAndErr;
import com.cskaoyan.bean.userManage.ItemAndTotal;
import com.cskaoyan.service.userManage.ShoppingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ShoppingAddressController {

    // 从容器中取出service
    @Autowired
    ShoppingAddressService shoppingAddressService;

    // 查询所有用户的地址信息
    @RequestMapping("/admin/address/list")
    @ResponseBody

    public DataAndErr queryShoppingAddress(String name, String userId, String sort, String order, int page, int limit) {
        DataAndErr dataAndErr = new DataAndErr();
        ItemAndTotal<Address> itemAndTotal = new ItemAndTotal<>();
        List<Address> addresses;

        if ((name == null || "".equals(name)) && (userId == null || "".equals(userId))) {
            // 查询全部
            addresses = shoppingAddressService.queryShoppingAddress();
            // 封装item和total
            itemAndTotal.setItems(addresses);
            itemAndTotal.setTotal(addresses.size());
        } else if ("".equals(userId) || userId == null) {
            // 只输入收货人名称
            // 根据收货人名称查询
            addresses = shoppingAddressService.queryAddressByUsername("%" + name + "%");
            // 封装item和total
            itemAndTotal.setItems(addresses);
            itemAndTotal.setTotal(addresses.size());
        } else if ("".equals(name) || name == null) {
            // 只输入用户id
            // 根据用户id查询
            addresses = shoppingAddressService.queryAddressByUserId(userId);
            // 封装item和total
            itemAndTotal.setItems(addresses);
            itemAndTotal.setTotal(addresses.size());
        } else {
            // 根据用户id和收货人姓名查询
            addresses = shoppingAddressService.queryAddressByUsernameAndUserId("%" + name + "%", userId);
            // 封装item和total
            itemAndTotal.setItems(addresses);
            itemAndTotal.setTotal(addresses.size());
        }

        // 封装data、errMsg、errno
        dataAndErr.setData(itemAndTotal);
        dataAndErr.setErrmsg("成功");
        dataAndErr.setErrno(0);

        // 返回这个JavaBean
        return dataAndErr;
    }



    /*DataAndErr queryShoppingAddress() {
        DataAndErr dataAndErr = new DataAndErr();
        ItemAndTotal<Address> addressItemAndTotal = new ItemAndTotal<>();
        // 调用service查询所有地址
        List<Address> addresses = shoppingAddressService.queryShoppingAddress();
        // 判断有没有查询出数据
        if (addresses != null) {
            // 封装itemAndTotal
            addressItemAndTotal.setItems(addresses);
            addressItemAndTotal.setTotal(addresses.size());
            // 封装dataAndErr
            dataAndErr.setData(addressItemAndTotal);
            dataAndErr.setErrmsg("成功");
            dataAndErr.setErrno(0);
        } else {
            dataAndErr.setErrmsg("失败");
            dataAndErr.setErrno(-1);
        }
        // 返回dataAndErr
        return dataAndErr;
    }*/
}
