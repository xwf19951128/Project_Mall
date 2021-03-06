package com.cskaoyan.service.wx.coreservice;

import com.cskaoyan.bean.admin.spread.MessageBean;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CoreService {


    MessageBean showCouponList(int page, int size, short status, HttpServletRequest request);

    MessageBean showGrouponList(int page, int size, short type, HttpServletRequest request);

    MessageBean showCollectList(int page, int size, short type, HttpServletRequest request);

    MessageBean showFootprintList(int page, int size, short type, HttpServletRequest request);
}
