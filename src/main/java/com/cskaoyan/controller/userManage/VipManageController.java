package com.cskaoyan.controller.userManage;

import com.cskaoyan.bean.userManage.DataAndErr;
import com.cskaoyan.bean.userManage.ItemAndTotal;
import com.cskaoyan.bean.userManage.User;
import com.cskaoyan.service.userManage.VipManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class VipManageController {

    // 从容器中取出service
    @Autowired
    VipManageService vipManageService;

    // 查询
    @RequestMapping("/admin/user/list")
    @ResponseBody
    public DataAndErr queryAllUser(String username, String mobile, String sort, String order, int page, int limit) {
        DataAndErr dataAndErr = new DataAndErr();
        ItemAndTotal<User> itemAndTotal = new ItemAndTotal<>();
        List<User> users;

        if ((username == null || "".equals(username)) && (mobile == null || "".equals(mobile))) {
            // 获取用户信息的集合
            users = vipManageService.queryAllUser();
            // 封装item和total
            itemAndTotal.setItems(users);
            itemAndTotal.setTotal(users.size());
        } else if ("".equals(mobile) || mobile == null) {
            // 只输入用户名
            // 根据用户名查询
            users = vipManageService.queryUserByUsername("%" + username + "%");
            // 封装item和total
            itemAndTotal.setItems(users);
            itemAndTotal.setTotal(users.size());
        } else if ("".equals(username) || username == null) {
            // 只输入手机号码
            // 根据手机号码查询
            users = vipManageService.queryUserByMobile(mobile);
            // 封装item和total
            itemAndTotal.setItems(users);
            itemAndTotal.setTotal(users.size());
        } else {
            // 根据用户名和手机号码查询
            users = vipManageService.queryUserByUsernameAndMobile("%" + username + "%", mobile);
            // 封装item和total
            itemAndTotal.setItems(users);
            itemAndTotal.setTotal(users.size());
        }

        // 封装data、errMsg、errno
        dataAndErr.setData(itemAndTotal);
        dataAndErr.setErrmsg("成功");
        dataAndErr.setErrno(0);

        // 返回这个JavaBean
        return dataAndErr;
    }
}
