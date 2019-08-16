package com.cskaoyan.controller.userManage;

import com.cskaoyan.bean.userManage.DataAndErr;
import com.cskaoyan.bean.userManage.ItemAndTotal;
import com.cskaoyan.bean.userManage.User;
import com.cskaoyan.service.userManage.VipManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class VipManageController {

    // 从容器中取出service
    @Autowired
    VipManageService vipManageService;

    // 查询所有用户信息
    @RequestMapping("/user/list")
    @ResponseBody
    public DataAndErr queryAllUser() {
        DataAndErr dataAndErr = new DataAndErr();
        ItemAndTotal<User> userItemAndTotal = new ItemAndTotal<>();
        // 获取用户信息的集合
        List<User> users = vipManageService.queryAllUser();
        // 判断有没有查到用户信息
        if (users != null) {
            // 封装item和total
            userItemAndTotal.setItems(users);
            userItemAndTotal.setTotal(users.size());
            // 封装data、errMsg、errno
            dataAndErr.setData(userItemAndTotal);
            dataAndErr.setErrmsg("成功");
            dataAndErr.setErrno(0);
        } else {
            dataAndErr.setErrmsg("失败");
            dataAndErr.setErrno(-1);
        }
        // 返回这个JavaBean
        return dataAndErr;
    }
}
