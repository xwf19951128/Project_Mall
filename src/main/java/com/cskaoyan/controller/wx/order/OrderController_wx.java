package com.cskaoyan.controller.wx.order;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.mall.order.GoodsDetail;
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

    /**
     * 查询商品详情
     * @param orderId 订单id
     * @return 模板返回
     */
    @RequestMapping("/wx/order/detail")
    @ResponseBody
    ResponseVo queryOrderDetail(int orderId) {
        // 用来封装data
        HashMap<String, Object> data = orderService_wx.queryOrderDetail(orderId);
        // 调用返回模板
        return ResponseUtil.success(data);
    }

    /**
     * 取消订单
     * @param map 注意看接收的是Json形式，所以用map接收
     * @return 没有data的返回
     */
    @RequestMapping("/wx/order/cancel")
    @ResponseBody
    ResponseVo cancelOrderByOrderId(@RequestBody HashMap<String, Object> map) {
        int orderId = (int) map.get("orderId");
        orderService_wx.cancelOrderByOrderId(orderId);
        return ResponseUtil.success();
    }

    /**
     * 付款
     * @param map 用来接收orderId
     * @return 返回失败模板，因为不用实现支付功能
     */
    @RequestMapping("/wx/order/prepay")
    @ResponseBody
    ResponseVo prepayOrder(@RequestBody HashMap<String, Object> map) {
        int orderId = (int) map.get("orderId");
        return ResponseUtil.fail(null, "订单不能支付", 724);
    }

    /**
     * 确认收货功能
     * @param map 用来接收orderId
     * @return 返回成功模板
     */
    @RequestMapping("/wx/order/confirm")
    @ResponseBody
    ResponseVo confirmOrder(@RequestBody HashMap<String, Object> map) {
        int orderId = (int) map.get("orderId");
        orderService_wx.confirmOrder(orderId);

        return ResponseUtil.success();
    }

    /**
     * 删除订单
     * @param map 注意看接收的是Json形式，所以用map接收
     * @return 没有data的返回
     */
    @RequestMapping("/wx/order/delete")
    @ResponseBody
    ResponseVo deleteOrderByOrderId(@RequestBody HashMap<String, Object> map) {
        int orderId = (int) map.get("orderId");
        orderService_wx.deleteOrderByOrderId(orderId);
        return ResponseUtil.success();
    }

    /**
     * 再次购买
     * @param id 商品的id
     * @return 返回模板
     */
    /*@RequestMapping("/wx/goods/related")
    @ResponseBody
    ResponseVo reBuyById(int id) {
        List<Goods> data = orderService_wx.reBuyById(id);

        return ResponseUtil.success(data);
    }*/

    /**
     * 申请退款
     * @param map 用map接收订单号
     * @return 模板返回
     */
    @RequestMapping("/wx/order/refund")
    @ResponseBody
    ResponseVo refundByOrderId(@RequestBody HashMap<String, Object> map) {
        int orderId = (int) map.get("orderId");
        orderService_wx.refundByOrderId(orderId);
        return ResponseUtil.success();
    }

    /**
     * 评论填写页面
     * @param orderId 订单号
     * @param goodsId 商品号
     * @return 模板返回
     */
    @RequestMapping("/wx/order/goods")
    @ResponseBody
    ResponseVo commentPage(int orderId, int goodsId) {
        GoodsDetail data = orderService_wx.queryOrderGoods(orderId, goodsId);

        return ResponseUtil.success(data);
    }
}
