package com.cskaoyan.service.wx.coreservice;

import com.cskaoyan.bean.admin.spread.MallCoupon;
import com.cskaoyan.bean.admin.spread.MessageBean;
import com.cskaoyan.bean.wx.coreservice.ListDateWX;

import com.cskaoyan.mapper.coreservice.CollectMapper;
import com.cskaoyan.mapper.coreservice.FootprintMapper;
import com.cskaoyan.mapper.coreservice.UserCouponMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CoreServiceImp implements CoreService{
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    FootprintMapper footprintMapper;
    @Autowired
    UserCouponMapper userCouponMapper;
//    String username= (String) SecurityUtils.getSubject().getPrincipal();
    String username="123";

    @Override
    public MessageBean showCouponList(int page, int size, short status) {
        PageHelper.startPage(page,size);
        ListDateWX dateWX=new ListDateWX();
        List<MallCoupon> list=userCouponMapper.queryUserCouponList(username,status);
        PageInfo<MallCoupon> pageInfo=new PageInfo(list);
        dateWX.setCount(pageInfo.getTotal());
        dateWX.setData(list);
        return new MessageBean("成功",0,dateWX);
    }
}
