package com.cskaoyan.service.wx.order.impl;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.mall.order.GoodsDetail;
import com.cskaoyan.bean.admin.mall.order.Order;
import com.cskaoyan.bean.wx.order.*;
import com.cskaoyan.mapper.goods.GoodsMapper;
import com.cskaoyan.mapper.mall.OrderMapper;
import com.cskaoyan.mapper.userManage.OrderMapper_wx;
import com.cskaoyan.service.wx.order.OrderService_wx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImpl_wx implements OrderService_wx {

    @Autowired
    OrderMapper_wx orderMapper_wx;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    GoodsMapper goodsMapper;

    /**
     * 查询全部订单
     * @param userId 当前用户的id
     * @return 订单集合
     */
    @Override
    public List<OrderVo> queryAllOrder(int userId) {
        // 创建一个空的orderVo集合，用来封装数据
        List<OrderVo> orderVos = new ArrayList<>();

        // 获取当前用户的全部信息
        List<Order> orderListByUserId = orderMapper.getOrderListBy(null, null, userId);

        // 封装
        for (Order order : orderListByUserId) {
            // 创建一个微信端订单模板
            OrderVo orderVo = new OrderVo();

            // 封装基本类型的信息
            orderVo.setActualPrice(order.getActualPrice().doubleValue());
            orderVo.setId(order.getId());
            orderVo.setOrderSn(order.getOrderSn());

            Short orderStatus = order.getOrderStatus();
            switch (orderStatus) {
                case 101:
                    orderVo.setOrderStatusText("未付款");
                    break;
                case 102:
                    orderVo.setOrderStatusText("用户取消");
                    break;
                case 103:
                    orderVo.setOrderStatusText("系统取消");
                    break;
                case 201:
                    orderVo.setOrderStatusText("已付款");
                    break;
                case 202:
                    orderVo.setOrderStatusText("申请退款");
                    break;
                case 203:
                    orderVo.setOrderStatusText("已退款");
                    break;
                case 301:
                    orderVo.setOrderStatusText("已发货");
                    break;
                case 401:
                    orderVo.setOrderStatusText("用户收货");
                    break;
                case 402:
                    orderVo.setOrderStatusText("系统收货");
                    break;
            }

            // 找到goodsList并封装
            List<Goods_wx> goodsVos = orderMapper_wx.queryGoodsListByOrderId(order.getId());
            orderVo.setGoodsList(goodsVos);

            orderVos.add(orderVo);
        }

        return orderVos;
    }

    /**
     * 查询待付款订单
     * @param showType 101
     * @param userId 当前用户的id
     * @return 订单集合
     */
    @Override
    public List<OrderVo> queryUnpayOrder(int showType, int userId) {

        // 获取当前用户未付款的订单的全部信息
        List<Order> orderListUnpay = orderMapper.getOrderListBy((short) showType, null, userId);

        // 创建一个空的orderVo集合，用来封装数据
        List<OrderVo> orderVos = new ArrayList<>();

        // 封装
        for (Order order : orderListUnpay) {
            // 创建一个微信端订单模板
            OrderVo orderVo = new OrderVo();

            // 封装基本类型的信息
            orderVo.setActualPrice(order.getActualPrice().doubleValue());
            orderVo.setId(order.getId());
            orderVo.setOrderSn(order.getOrderSn());
            orderVo.setOrderStatusText("未付款");

            // 找到goodsList并封装
            List<Goods_wx> goodsVos = orderMapper_wx.queryGoodsListByOrderId(order.getId());
            orderVo.setGoodsList(goodsVos);

            // 封装HandlerOption
            HandleOption handleOption = new HandleOption();
            handleOption.setCancel(true);
            handleOption.setPay(true);
            orderVo.setHandleOption(handleOption);

            orderVos.add(orderVo);
        }

        return orderVos;
    }

    /**
     * 查询待发货订单
     * @param showType 201
     * @param userId 当前用户的id
     * @return 订单集合
     */
    @Override
    public List<OrderVo> queryUndeliveryOrder(int showType, int userId) {

        // 创建一个空的orderVo集合，用来封装数据
        List<OrderVo> orderVos = new ArrayList<>();

        // 获取当前用户未发货的全部信息
        List<Order> orderListUndelivery = orderMapper.getOrderListBy((short) showType, null, userId);

        // 封装
        for (Order order : orderListUndelivery) {
            // 创建一个微信端订单模板
            OrderVo orderVo = new OrderVo();

            // 封装基本类型的信息
            orderVo.setActualPrice(order.getActualPrice().doubleValue());
            orderVo.setId(order.getId());
            orderVo.setOrderSn(order.getOrderSn());
            orderVo.setOrderStatusText("已付款");

            // 封装HandlerOption
            HandleOption handleOption = new HandleOption();
            handleOption.setRefund(true);
            orderVo.setHandleOption(handleOption);

            // 找到goodsList并封装
            List<Goods_wx> goodsVos = orderMapper_wx.queryGoodsListByOrderId(order.getId());
            orderVo.setGoodsList(goodsVos);

            orderVos.add(orderVo);
        }

        return orderVos;
    }

    /**
     * 查询待收货订单
     * @param showType 301
     * @param userId 当前用户的id
     * @return 订单集合
     */
    @Override
    public List<OrderVo> queryUnreceiptOrder(int showType, int userId) {

        // 创建一个空的orderVo集合，用来封装数据
        List<OrderVo> orderVos = new ArrayList<>();

        // 获取当前用户已发货的订单的全部信息
        List<Order> orderListUnreceipt = orderMapper.getOrderListBy((short) showType, null, userId);

        // 封装
        for (Order order : orderListUnreceipt) {
            // 创建一个微信端订单模板
            OrderVo orderVo = new OrderVo();

            // 封装基本类型的信息
            orderVo.setActualPrice(order.getActualPrice().doubleValue());
            orderVo.setId(order.getId());
            orderVo.setOrderSn(order.getOrderSn());
            orderVo.setOrderStatusText("已发货");

            // 封装HandlerOption
            HandleOption handleOption = new HandleOption();
            handleOption.setConfirm(true);
            orderVo.setHandleOption(handleOption);

            // 找到goodsList并封装
            List<Goods_wx> goodsVos = orderMapper_wx.queryGoodsListByOrderId(order.getId());
            orderVo.setGoodsList(goodsVos);

            orderVos.add(orderVo);
        }

        return orderVos;
    }

    /**
     * 查询待评价订单
     * @param showType 401
     * @param userId 当前用户的id
     * @return 订单集合
     */
    @Override
    public List<OrderVo> queryUnjudgeOrder(int showType, int userId) {

        // 创建一个空的orderVo集合，用来封装数据
        List<OrderVo> orderVos = new ArrayList<>();

        // 获取当前用户未付款的订单的全部信息
        List<Order> orderListUnjudge = orderMapper.getOrderListBy((short) showType, null, userId);

        // 封装
        for (Order order : orderListUnjudge) {
            // 创建一个微信端订单模板
            OrderVo orderVo = new OrderVo();

            // 封装基本类型的信息
            orderVo.setActualPrice(order.getActualPrice().doubleValue());
            orderVo.setId(order.getId());
            orderVo.setOrderSn(order.getOrderSn());
            orderVo.setOrderStatusText("已收货");

            // 封装HandlerOption
            HandleOption handleOption = new HandleOption();
            handleOption.setComment(true);
            handleOption.setRebuy(true);
            orderVo.setHandleOption(handleOption);

            // 找到goodsList并封装
            List<Goods_wx> goodsVos = orderMapper_wx.queryGoodsListByOrderId(order.getId());
            orderVo.setGoodsList(goodsVos);

            orderVos.add(orderVo);
        }

        return orderVos;
    }

    /**
     * 查询订单详情
     * @param orderId 订单id
     * @return 返回一个封装号的map
     */
    @Override
    public HashMap<String, Object> queryOrderDetail(int orderId) {
        // 用map封装
        HashMap<String, Object> hashMap = new HashMap<>();

        // 根据订单id获取商品详情
        List<GoodsDetail> orderGoods = orderMapper.getOrderGoodsById(orderId);

        // HandleOption用来各个订单的状态信息
        HandleOption handleOption = new HandleOption();

        /*// 根据订单id获取订单
        OrderInfo orderExample = orderMapper_wx.getOrderByOrderId(orderId);

        // 创建一个空的orderInfo 用来封装
        OrderInfo orderInfo = new OrderInfo();*/

        OrderInfo orderInfo = orderMapper_wx.getOrderByOrderId(orderId);

        // 封装普通信息
        /*orderInfo.setId(orderExample.getId());
        orderInfo.setActualPrice(orderExample.getActualPrice());
        orderInfo.setAddTime(orderExample.getAddTime());
        orderInfo.setAddress(orderExample.getAddress());
        orderInfo.setConsignee(orderExample.getConsignee());
        orderInfo.setCouponPrice(orderExample.getCouponPrice());
        orderInfo.setFreightPrice(orderExample.getFreightPrice());
        orderInfo.setGoodsPrice(orderExample.getGoodsPrice());
        orderInfo.setMobile(orderExample.getMobile());
        orderInfo.setOrderSn(orderExample.getOrderSn());*/


        // 封装订单状态
        // 并且需要根据订单状态设置HandleOption
        int orderStatus = orderMapper_wx.getOrderStatusByOrderId(orderId);
        if (orderStatus == 101) {
            orderInfo.setOrderStatusText("未付款");
            handleOption.setCancel(true);
            handleOption.setPay(true);
            orderInfo.setHandleOption(handleOption);
        } else if (orderStatus == 201) {
            orderInfo.setOrderStatusText("未发货");
            handleOption.setRefund(true);
            orderInfo.setHandleOption(handleOption);
        } else if (orderStatus == 301) {
            orderInfo.setOrderStatusText("已发货");
            handleOption.setConfirm(true);
            orderInfo.setHandleOption(handleOption);
        } else if (orderStatus == 401) {
            orderInfo.setOrderStatusText("未评价");
            handleOption.setComment(true);
            handleOption.setDelete(true);
            handleOption.setRebuy(true);
            orderInfo.setHandleOption(handleOption);
        }

        // 封装到map
        hashMap.put("orderGoods", orderGoods);
        hashMap.put("orderInfo", orderInfo);

        return hashMap;
    }

    /**
     * 根据订单号取消订单
     * @param orderId 订单号
     */
    @Override
    public void cancelOrderByOrderId(int orderId) {
        orderMapper_wx.cancelOrderByOrderId(102, orderId);

    }

    /**
     * 确认订单
     * @param orderId 订单号
     */
    @Override
    public void confirmOrder(int orderId) {
        // 401 代表已收货
        orderMapper_wx.confirmOrder(401, orderId);

    }

    /**
     * 删除订单
     * @param orderId 订单号
     */
    @Override
    public void deleteOrderByOrderId(int orderId) {
        orderMapper_wx.deleteOrderByOrderId(orderId);
        orderMapper_wx.deleteOrder_GoodsByOrderId(orderId);
    }

    /**
     * 再次购买功能
     * @param id 商品的id号
     * @return 返回商品的list集合
     */
    /*@Override
    public List<Goods> reBuyById(int id) {
        Goods goods = goodsMapper.findGoods(id);

        ArrayList<Goods> list = new ArrayList<>();
        list.add(goods);

        return list;
    }*/

    /**
     * 申请退款
     * @param orderId 订单号
     */
    @Override
    public void refundByOrderId(int orderId) {
        // 202 代表申请退款
        orderMapper_wx.refundByOrderId(202, orderId);
    }

    /**
     * 评价页面
     * @param orderId
     * @param goodsId
     * @return
     */
    @Override
    public GoodsDetail queryOrderGoods(int orderId, int goodsId) {
        GoodsDetail goodsDetail = orderMapper_wx.queryOrderGoods(orderId, goodsId);

        return goodsDetail;
    }

    /**
     * 提交评论
     * @param map 前端的数据封装在map
     * @param userId 当前登录用户的id
     */
    @Override
    public void insertComment(HashMap<String, Object> map, int userId) {
        String content = (String) map.get("content");
        Boolean hasPicture = (Boolean) map.get("hasPicture");
        int orderGoodsId = (int) map.get("orderGoodsId");
        List<String> picUrls = (List<String>) map.get("picUrls");
        int star = (int) map.get("star");
        orderMapper_wx.insertComment(content, hasPicture, orderGoodsId, star, userId);
    }
}
