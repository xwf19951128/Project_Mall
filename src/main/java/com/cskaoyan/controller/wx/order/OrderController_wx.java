package com.cskaoyan.controller.wx.order;

import com.cskaoyan.bean.wx.order.DataVo;
import com.cskaoyan.bean.wx.order.OrderVo;
import com.cskaoyan.service.wx.order.OrderService_wx;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController_wx {

    @Autowired
    OrderService_wx orderService_wx;

    @RequestMapping("/wx/order/list")
    @ResponseBody
    ResponseVo queryOrder(int showType, int page, int size) {
        // 创建一个返回模板
        ResponseVo<OrderVo> responseVo = new ResponseVo<>();

        // 创建一个data的JOPO
        DataVo<OrderVo> data = new DataVo<>();

        // 创建一个订单的集合
        List<OrderVo> list = new ArrayList<>();

        // 根据show type判断入口的状态
        if (showType == 0) {
            // 全部订单
            list = orderService_wx.queryAllOrder();

        } else if (showType == 1) {
            // 待付款
            list = orderService_wx.queryUnpayOrder();
        } else if (showType == 2) {
            // 代发货
            list = orderService_wx.queryUndeliveryOrder();
        } else if (showType == 3) {
            // 待收获
            list =  orderService_wx.queryUnreceiptOrder();
        } else if (showType == 4) {
            // 待评价
            list = orderService_wx.queryUnjudgeOrder();
        }
        data.setData(list);
        data.setCount(list.size());
        data.setTotalPages(0);

        return ResponseUtil.success(data);
    }


}
