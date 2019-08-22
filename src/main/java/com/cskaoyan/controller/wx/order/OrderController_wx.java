package com.cskaoyan.controller.wx.order;

import com.cskaoyan.bean.admin.mall.order.Order;
import com.cskaoyan.bean.wx.order.DataForOrder;
import com.cskaoyan.bean.wx.order.OrderVo;
import com.cskaoyan.service.wx.order.OrderService_wx;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.cskaoyan.util.wx.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class OrderController_wx {

    @Autowired
    OrderService_wx orderService_wx;

    /**
     * 我的订单模块
     * @param showType 0全部订单 1待付款 2待发货 3待收货 4待评价
     * @param page 页数
     * @param size 每一页的商品个数
     * @param request 用来获取当前的userId
     * @return 返回response模板
     */
    @RequestMapping("/wx/order/list")
    @ResponseBody
    ResponseVo queryOrder(int showType, int page, int size, HttpServletRequest request) {

        // 获取当前登录用户的id
        String tokenKey = request.getHeader("X-Litemall-Token");
        int userId = UserTokenManager.getUserId(tokenKey).intValue();

        // 创建一个返回模板
        ResponseVo<OrderVo> responseVo = new ResponseVo<>();

        // 创建一个data的JOPO
        DataForOrder<OrderVo> data = new DataForOrder<>();

        // 创建一个订单的集合
        List<OrderVo> list = new ArrayList<>();

        // 根据show type判断入口的状态
        if (showType == 0) {
            // 全部订单
            list = orderService_wx.queryAllOrder(userId);
        } else if (showType == 1) {
            // 待付款, 101代表未付款
            list = orderService_wx.queryUnpayOrder(101, userId);
        } else if (showType == 2) {
            // 代发货
            list = orderService_wx.queryUndeliveryOrder(201, userId);
        } else if (showType == 3) {
            // 待收获
            list =  orderService_wx.queryUnreceiptOrder(301, userId);
        } else if (showType == 4) {
            // 待评价
            list = orderService_wx.queryUnjudgeOrder(401, userId);
        }

        // 封装ResponseVo的data数据
        data.setData(list);
        data.setCount(list.size());
        data.setTotalPages(list.size() / size + 1);

        return ResponseUtil.success(data);
    }


    @RequestMapping("/wx/order/detail")
    @ResponseBody
    ResponseVo queryOrderDetail(int orderId) {
        // 用来封装data
        HashMap<String, Object> data;
        data = orderService_wx.queryOrderDetail(orderId);

        return ResponseUtil.success(data);
    }


}
