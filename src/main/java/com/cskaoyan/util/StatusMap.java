package com.cskaoyan.util;

import com.cskaoyan.bean.wx.order.HandleOption;

import java.util.HashMap;
import java.util.Map;

public class StatusMap {
    public static final Map<Integer,String> statusMap=new HashMap<Integer, String>(){{
        put(101,"未付款");
        put(102,"用户取消");
        put(103,"系统取消");
        put(201,"已付款");
        put(203,"已退款");
        put(202,"申请退款");
        put(301,"已发货");
        put(401,"用户收货");
        put(402,"系统收货");
    }
    };
    public static HandleOption handleOptionMaker(int status,int comments){
        HandleOption handleOption=new HandleOption();
        switch (status){
            case 201:
                handleOption.setPay(true);
            case 402:
                handleOption.setConfirm(true);
            case 401:handleOption.setConfirm(true);
            case 202:handleOption.setRefund(true);
            case 203:handleOption.setDelete(true);
            break;
        }
        if(comments>0){
            handleOption.setComment(true);
        }
        return handleOption;
    }
}
