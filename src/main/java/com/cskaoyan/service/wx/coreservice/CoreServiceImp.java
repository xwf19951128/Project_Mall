package com.cskaoyan.service.wx.coreservice;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.spread.GrouponInfo;
import com.cskaoyan.bean.admin.spread.MallCoupon;
import com.cskaoyan.bean.admin.spread.MallGroupon;
import com.cskaoyan.bean.admin.spread.MessageBean;
import com.cskaoyan.bean.wx.coreservice.*;

import com.cskaoyan.mapper.coreservice.CollectMapper;
import com.cskaoyan.mapper.coreservice.FootprintMapper;
import com.cskaoyan.mapper.coreservice.UserCouponMapper;
import com.cskaoyan.mapper.goods.GoodsMapper;
import com.cskaoyan.mapper.login.WxUserMapper;
import com.cskaoyan.mapper.spread.MallCouponMapper;
import com.cskaoyan.mapper.spread.MallGrouponMapper;
import com.cskaoyan.util.StatusMap;
import com.cskaoyan.util.wx.TokenUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.HTMLDocument;
import java.util.*;

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
    @Autowired
    MallCouponMapper couponMapper;
//    String username= (String) SecurityUtils.getSubject().getPrincipal();

    @Override
    public MessageBean showCouponList(int page, int size, short status, HttpServletRequest request) {
//        int uid=TokenUtil.getActiveUserid(request);
        int uid=23;
        String username= userMapper.selectByPrimaryKey(uid).getUsername();
        PageHelper.startPage(page,size);
        ListDateWX dateWX=new ListDateWX();
        List<UserCoupon> list=userCouponMapper.queryUserCouponList(username,status);
        list=judgeCoupon(list);
        PageInfo<UserCoupon> pageInfo=new PageInfo(list);
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
        Collect get=collectMapper.selectByValueId(valueId);
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
        GrouponInfoWx grouponInfoWx= grouponMapper.getGrouponDetail(grouponId);
        return new MessageBean("成功",0,grouponInfoWx);
    }

    @Override
    public MessageBean getCoupon(String code, HttpServletRequest request) {
        //        int uid=TokenUtil.getActiveUserid(request);
        int uid=23;
        MallCoupon coupon=couponMapper.queryByCode(code);
        if(coupon==null||coupon.getDeleted()==true){
            return new MessageBean("优惠券不正确",742,null);
        }
        if(userCouponMapper.countByCid(coupon.getId())>0){
            return new MessageBean("优惠券已领取",741,null);
        }
        Date date=new Date();
        UserCoupon userCoupon=new UserCoupon();
        userCoupon.setAddTime(date);
        userCoupon.setUserId(uid);
        userCoupon.setCouponId(coupon.getId());
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,coupon.getDays());
        userCoupon.setStartTime(date);
        userCoupon.setEndTime(calendar.getTime());
        if(userCouponMapper.insertSelective(userCoupon)==1){
            return new MessageBean("成功",0,null);
        }
        return new MessageBean("优惠券不正确",742,null);
    }

    @Override
    public MessageBean receiveCoupon(String couponId, HttpServletRequest request) {
        //        int uid=TokenUtil.getActiveUserid(request);
        int uid=23;
        UserCoupon userCoupon=new UserCoupon();
        MallCoupon mallCoupon=couponMapper.selectByPrimaryKey(Integer.valueOf(couponId));
        return null;
    }

    public List judgeCoupon(List<UserCoupon> list){
        Date date=new Date();
        List<UserCoupon> newlist=new ArrayList<>();
        Iterator iterator=list.iterator();
        UserCoupon coupon=null;
        while (iterator.hasNext()){
            coupon= (UserCoupon) iterator.next();
            if(coupon.getEndTime().before(date)){
                coupon.setStatus((short) 2);
                newlist.add(coupon);
                iterator.remove();
            }
        }
//        for (UserCoupon coupon : list) {1
//            if(coupon.getEndTime().before(date)){
//                coupon.setStatus((short) 2);
//                list.;
//                newlist.add(coupon);
//            }
//        }
        if(newlist.size()>0){
            userCouponMapper.updateStatus(newlist);
        }
        return list;
    }
}
