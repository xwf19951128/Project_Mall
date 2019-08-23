package com.cskaoyan.service.wx.coreservice;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.spread.*;
import com.cskaoyan.bean.wx.coreservice.*;

import com.cskaoyan.bean.wx.coreservice.UserCoupon;
import com.cskaoyan.bean.wx.login.WxUser;
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
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
        int uid=TokenUtil.getActiveUserid(request);
        if(uid>0){
        String username= userMapper.selectByPrimaryKey(uid).getUsername();
        PageHelper.startPage(page,size);
        ListDateWX dateWX=new ListDateWX();
        List<UserCoupon> list=userCouponMapper.queryUserCouponList(username,status);
        if(status==0){
        list=judgeCoupon(list);}
        PageInfo<UserCoupon> pageInfo=new PageInfo(list);
        dateWX.setCount(pageInfo.getTotal());
        dateWX.setData(list);
        return new MessageBean("成功",0,dateWX);}
        return new MessageBean("请登录",501,null);
    }



    @Override
    public MessageBean showGrouponList(short showType,HttpServletRequest request) {
        int uid=TokenUtil.getActiveUserid(request);
        if(uid>0){
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
        return new MessageBean("成功",0,dateWX);}
        return new MessageBean("请登录",501,null);
    }

    @Override
    public MessageBean showCollectList(int page, int size, short type,HttpServletRequest request) {
        int uid=TokenUtil.getActiveUserid(request);
        if(uid>0){
        PageHelper.startPage(page,size);
        CollectList dateWX=new CollectList();
        List<CollectGoods> list=collectMapper.queryListByUser(uid,type);
        PageInfo<CollectGoods> pageInfo=new PageInfo(list);
        dateWX.setTotalPages(pageInfo.getTotal());
        dateWX.setCollectList(list);
        return new MessageBean("成功",0,dateWX);}
        return new MessageBean("请登录",501,null);
    }

    @Override
    public MessageBean showFootprintList(int page, int size, HttpServletRequest request) {
        int uid=TokenUtil.getActiveUserid(request);
        if(uid>0){
        PageHelper.startPage(page,size);
        FootList dateWX=new FootList();
        List<CollectGoods> list=footprintMapper.queryListByUser(uid);
        PageInfo<CollectGoods> pageInfo=new PageInfo(list);
        dateWX.setTotalPages(pageInfo.getTotal());
        dateWX.setFootprintList(list);
        return new MessageBean("成功",0,dateWX);}
        return new MessageBean("请登录",501,null);
    }

    @Override
    public MessageBean insertCollect(int valueId, HttpServletRequest request) {
        int uid=TokenUtil.getActiveUserid(request);
        if(uid>0){
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
        }}
        return new MessageBean("请登录",501,null);
    }

    @Override
    public MessageBean getGrouponDetail(int grouponId, HttpServletRequest request) {
        GrouponInfoWx grouponInfoWx= grouponMapper.getGrouponDetail(grouponId);
        return new MessageBean("成功",0,grouponInfoWx);
    }

    @Override
    public MessageBean getCoupon(String code, HttpServletRequest request) {
        int uid=TokenUtil.getActiveUserid(request);
        if(uid<0){return new MessageBean("请登录",501,null);}
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
int uid=TokenUtil.getActiveUserid(request);
        //获取认证后的用户信息，通过Realm进行封装的
        if(uid<0){
            return new MessageBean("请登录",501,null);
        }else{
            //查出该用户是否有该优惠券
            UserCouponExample userCouponExample = new UserCouponExample();
            UserCouponExample.Criteria criteria = userCouponExample.createCriteria();
            criteria.andCouponIdEqualTo(Integer.valueOf(couponId)).andUserIdEqualTo(uid);
            List<UserCoupon> userCoupons =userCouponMapper.selectByExample(userCouponExample);
            if(userCoupons.size()>0){
                return new MessageBean("优惠券已经领取过",740,null);
            }
            MallCoupon mallCoupon=couponMapper.selectByPrimaryKey(Integer.valueOf(couponId));
            //找出该优惠券
            //然后把该优惠券塞进用户
            Date date=new Date();
            if(mallCoupon.getStartTime()==null||date.before(mallCoupon.getEndTime())){
            UserCoupon userCoupon=new UserCoupon();
            userCoupon.setUserId(uid);
            userCoupon.setStatus((short) 0);
            userCoupon.setCouponId(mallCoupon.getId());
            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.DATE,mallCoupon.getDays());
            Date startdate=(mallCoupon.getStartTime()==null||date.after(mallCoupon.getStartTime()))?date:mallCoupon.getStartTime();
            userCoupon.setStartTime(startdate);
            Date enddate=(mallCoupon.getEndTime()==null||calendar.getTime().before(mallCoupon.getEndTime()))?calendar.getTime():mallCoupon.getEndTime();
            userCoupon.setEndTime(enddate);
            userCoupon.setAddTime(date);
            userCouponMapper.insert(userCoupon);
            return new MessageBean("成功",0,null);
            }else {
                return new MessageBean("已过期的优惠卷",777,null);
            }
        }
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
