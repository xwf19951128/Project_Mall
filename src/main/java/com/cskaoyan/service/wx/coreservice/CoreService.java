package com.cskaoyan.service.wx.coreservice;

import com.cskaoyan.bean.admin.spread.MessageBean;


import java.util.List;

public interface CoreService {

    MessageBean showCouponList(int page, int size, short status);
}
