package com.cskaoyan.service.wx.coreservice;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.spread.MallCoupon;
import com.cskaoyan.bean.admin.spread.MallGroupon;
import com.cskaoyan.bean.admin.spread.MessageBean;
import com.cskaoyan.bean.wx.coreservice.*;

import com.cskaoyan.mapper.coreservice.CollectMapper;
import com.cskaoyan.mapper.coreservice.FootprintMapper;
import com.cskaoyan.mapper.coreservice.UserCouponMapper;
import com.cskaoyan.mapper.login.WxUserMapper;
import com.cskaoyan.mapper.spread.MallGrouponMapper;
import com.cskaoyan.util.wx.TokenUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service
public class CoreServiceImp implements CoreService{
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    FootprintMapper footprintMapper;
    @Autowired
    UserCouponMapper userCouponMapper;
    @Autowired
    WxUserMapper userMapper;
    @Autowired
    MallGrouponMapper grouponMapper;
//    String username= (String) SecurityUtils.getSubject().getPrincipal();

    @Override
    public MessageBean showCouponList(int page, int size, short status, HttpServletRequest request) {
        int uid=TokenUtil.getActiveUserid(request);
        String username= userMapper.selectByPrimaryKey(uid).getUsername();
        PageHelper.startPage(page,size);
        ListDateWX dateWX=new ListDateWX();
        List<MallCoupon> list=userCouponMapper.queryUserCouponList(username,status);
        PageInfo<MallCoupon> pageInfo=new PageInfo(list);
        dateWX.setCount(pageInfo.getTotal());
        dateWX.setData(list);
        return new MessageBean("成功",0,dateWX);
    }



    @Override
    public MessageBean showGrouponList(short showType,HttpServletRequest request) {
        int uid=TokenUtil.getActiveUserid(request);
        ListDateWX dateWX=new ListDateWX();
        List<MallGroupon> list=grouponMapper.queryListByUser(uid,showType);
        PageInfo<MallGroupon> pageInfo=new PageInfo(list);
        dateWX.setCount(pageInfo.getTotal());
        dateWX.setData(list);
        return new MessageBean("成功",0,dateWX);
    }

    @Override
    public MessageBean showCollectList(int page, int size, short type,HttpServletRequest request) {
        int uid=TokenUtil.getActiveUserid(request);
        PageHelper.startPage(page,size);
        CollectList dateWX=new CollectList();
        List<Goods> list=collectMapper.queryListByUser(uid,type);
        PageInfo<Goods> pageInfo=new PageInfo(list);
        dateWX.setTotalPages(pageInfo.getTotal());
        dateWX.setCollectList(list);
        return new MessageBean("成功",0,dateWX);
    }

    @Override
    public MessageBean showFootprintList(int page, int size, HttpServletRequest request) {
        int uid=TokenUtil.getActiveUserid(request);
        PageHelper.startPage(page,size);
        FootList dateWX=new FootList();
        List<Footprint> list=footprintMapper.queryListByUser(uid);
        PageInfo<Footprint> pageInfo=new PageInfo(list);
        dateWX.setTotalPages(pageInfo.getTotal());
        dateWX.setFootprintList(list);
        return new MessageBean("成功",0,dateWX);
    }
}
