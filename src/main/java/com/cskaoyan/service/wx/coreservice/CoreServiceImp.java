package com.cskaoyan.service.wx.coreservice;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.spread.MallCoupon;
import com.cskaoyan.bean.admin.spread.MallGroupon;
import com.cskaoyan.bean.admin.spread.MessageBean;
import com.cskaoyan.bean.wx.coreservice.*;

import com.cskaoyan.mapper.coreservice.CollectMapper;
import com.cskaoyan.mapper.coreservice.FootprintMapper;
import com.cskaoyan.mapper.coreservice.UserCouponMapper;
import com.cskaoyan.mapper.goods.GoodsMapper;
import com.cskaoyan.mapper.login.WxUserMapper;
import com.cskaoyan.mapper.spread.MallGrouponMapper;
import com.cskaoyan.util.StatusMap;
import com.cskaoyan.util.wx.TokenUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    GoodsMapper goodsMapper;
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
//        int uid=TokenUtil.getActiveUserid(request);
        int uid=23;
        ListDateWX dateWX=new ListDateWX();
        List<RabbishBean> list=grouponMapper.queryListByUser(uid,showType);
        for (RabbishBean bean : list) {
            bean.setOrderStatusText(StatusMap.statusMap.get(bean.getOrderStatus()));
            bean.setJoinerCount(Math.toIntExact(grouponMapper.countByGroupon(bean.getGroupon().getGrouponId())));
            bean.setCreator(userMapper.selectByPrimaryKey(bean.getCreatorId()).getUsername());
            bean.setHandleOption(StatusMap.handleOptionMaker(bean.getOrderStatus(),bean.getComments()));
            if(bean.getCreatorId()==uid){
                bean.setCreatorOwner(true);
            }else {
                bean.setCreatorOwner(false);
            }
        }
        PageInfo<RabbishBean> pageInfo=new PageInfo(list);
        dateWX.setCount(pageInfo.getTotal());
        dateWX.setData(list);
        return new MessageBean("成功",0,dateWX);
    }

    @Override
    public MessageBean showCollectList(int page, int size, short type,HttpServletRequest request) {
//        int uid=TokenUtil.getActiveUserid(request);
        int uid=23;
        PageHelper.startPage(page,size);
        CollectList dateWX=new CollectList();
        List<CollectGoods> list=collectMapper.queryListByUser(uid,type);
        PageInfo<CollectGoods> pageInfo=new PageInfo(list);
        dateWX.setTotalPages(pageInfo.getTotal());
        dateWX.setCollectList(list);
        return new MessageBean("成功",0,dateWX);
    }

    @Override
    public MessageBean showFootprintList(int page, int size, HttpServletRequest request) {
//        int uid=TokenUtil.getActiveUserid(request);
        int uid=23;
        PageHelper.startPage(page,size);
        FootList dateWX=new FootList();
        List<CollectGoods> list=footprintMapper.queryListByUser(uid);
        PageInfo<CollectGoods> pageInfo=new PageInfo(list);
        dateWX.setTotalPages(pageInfo.getTotal());
        dateWX.setFootprintList(list);
        return new MessageBean("成功",0,dateWX);
    }

    @Override
    public MessageBean insertCollect(int valueId, HttpServletRequest request) {
//        int uid=TokenUtil.getActiveUserid(request);
        int uid=23;
        Collect get=collectMapper.selectByPrimaryKey(valueId);
        Map map=new HashMap();
        if(get==null){
            Collect collect=new Collect();
            collect.setAddTime(new Date());
            collect.setUpdateTime(new Date());
            collect.setValueId(valueId);
            collect.setType((byte) 0);
            collect.setUserId(uid);
            collect.setDeleted(false);
            collectMapper.insert(collect);
            map.put("type","add");
            return new MessageBean("成功",0,map);
        }else {
            collectMapper.deleteByPrimaryKey(get.getId());
            map.put("type","delete");
            return new MessageBean("成功",0,map);
        }
    }

    @Override
    public MessageBean getGrouponDetail(int grouponId, HttpServletRequest request) {
        return null;
    }
}
