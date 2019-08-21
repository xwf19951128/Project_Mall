package com.cskaoyan.controller.admin.mall;

import com.cskaoyan.bean.admin.mall.order.Order;
import com.cskaoyan.bean.admin.mall.order.OrderDetail;
import com.cskaoyan.bean.admin.mall.order.OrderPage;
import com.cskaoyan.service.admin.mall.OrderService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("list")
    public ResponseVo getOrderList(int page, int limit, Short orderStatus, String sort, String order, String orderSn,Integer userId) {
        String orderBy = sort + " " + order;
        PageHelper.startPage(page,limit,orderBy);
        List<Order> items =  orderService.getOrderList(orderStatus,orderSn,userId);
        OrderPage orderPage = new OrderPage();
        PageInfo<Order> orderPageInfo = new PageInfo<>(items);
        orderPage.setItems(items);
        orderPage.setTotal((int)orderPageInfo.getTotal());
        return ResponseUtil.success(orderPage);
    }

    @RequestMapping("detail")
    public ResponseVo getOrderDetailById(int id) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(orderService.getOrderById(id));
        orderDetail.setOrderGoods(orderService.getOrderGoodsById(id));
        orderDetail.setUser(orderService.getOrderUserById(id));
        return ResponseUtil.success(orderDetail);
    }
}
