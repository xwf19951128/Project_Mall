package com.cskaoyan.service.speard;

import com.cskaoyan.bean.spread.*;
import com.cskaoyan.mapper.spread.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SpreadServiceImp implements SpreadService{
    @Autowired
    MallADMapper adMapper;
    @Autowired
    MallGrouponMapper grouponMapper;
    @Autowired
    MallGrouponRuleMapper ruleMapper;
    @Autowired
    MallTopicMapper topicMapper;
    @Autowired
    MallCouponMapper couponMapper;
    @Override
    public MessageBean<MallTopic> updateRecord(MallTopic ad) {
        if(topicMapper.updateByPrimaryKey(ad)==1){
            return new MessageBean<>("成功",0,ad);
        }
        return new MessageBean<>("失败",1,ad);
    }

    @Override
    public MessageBean<MallTopic> addRecord(MallTopic ad) {
        if(topicMapper.insert(ad)==1){
            return new MessageBean<>("成功",0,ad);
        }
        return new MessageBean<>("失败",1,ad);
    }

    @Override
    public MessageBean<String> deleteRecord(MallTopic ad) {
        if(topicMapper.deleteByPrimaryKey(ad.getId())==1){
            return new MessageBean<>("成功",0,null);
        }
        return new MessageBean<>("失败",1,null);
    }

    @Override
    public MessageBean<ListDate<MallTopic>> showTopicListByPage(int page, int limit, String title, String subtitle) {
        if(title!=null){
            title="%"+title+"%";}
        if(subtitle!=null){
            subtitle="%"+subtitle+"%";}
        PageHelper.startPage(page,limit);
        ArrayList<MallTopic> list=topicMapper.queryMallTopicList(title,subtitle);
        PageInfo<MallTopic> pageInfo=new PageInfo<>(list);
        ListDate<MallTopic> listDate=new ListDate<>();
        listDate.setTotal(pageInfo.getTotal());
        listDate.setItems(list);
        return new MessageBean<ListDate<MallTopic>>("成功",0,listDate);
    }

    @Override
    public MessageBean<ListDate<MallAD>> showADListByPage(int page, int limit, String content, String name) {
        PageHelper.startPage(page,limit);
        if(content!=null){
            content="%"+content+"%";}
        if(name!=null){
            name="%"+name+"%";}
        ArrayList<MallAD> list=adMapper.queryMallADList(content,name);
        PageInfo<MallAD> pageInfo=new PageInfo<>(list);
        ListDate<MallAD> listDate=new ListDate<>();
        listDate.setTotal(pageInfo.getTotal());
        listDate.setItems(list);
        return new MessageBean<ListDate<MallAD>>("成功",0,listDate);
    }

    @Override
    public MessageBean<MallAD> updateRecord(MallAD ad) {
        if(adMapper.updateByPrimaryKey(ad)==1){
            return new MessageBean<>("成功",0,ad);
        }
        return new MessageBean<>("失败",1,ad);
    }

    @Override
    public MessageBean<MallAD> addRecord(MallAD ad) {
        if(adMapper.insert(ad)==1){
            return new MessageBean<>("成功",0,ad);
        }
        return new MessageBean<>("失败",1,ad);
    }

    @Override
    public MessageBean<String> deleteRecord(MallAD ad) {
        if(adMapper.deleteByPrimaryKey(ad.getId())==1){
            return new MessageBean<>("成功",0,null);
        }
        return new MessageBean<>("失败",1,null);
    }

    @Override
    public MessageBean<ListDate<MallGrouponRule>> showGrouponRuleListByPage(int page, int limit, String goodsId) {
        PageHelper.startPage(page,limit);
        ArrayList<MallGrouponRule> list=ruleMapper.queryMallGrouponRuleList(goodsId);
        PageInfo<MallGrouponRule> pageInfo=new PageInfo<>(list);
        ListDate<MallGrouponRule> listDate=new ListDate<>();
        listDate.setTotal(pageInfo.getTotal());
        listDate.setItems(list);
        return new MessageBean<ListDate<MallGrouponRule>>("成功",0,listDate);
    }

    @Override
    public MessageBean<MallGrouponRule> updateRecord(MallGrouponRule ad) {
        if(ruleMapper.updateByPrimaryKey(ad)==1){
            return new MessageBean<>("成功",0,ad);
        }
        return new MessageBean<>("失败",1,ad);
    }

    @Override
    public MessageBean<MallGrouponRule> addRecord(MallGrouponRule ad) {
        if(ruleMapper.insertSelective(ad)==1){
            return new MessageBean<>("成功",0,ad);
        }
        return new MessageBean<>("失败",1,ad);
    }

    @Override
    public MessageBean<String> deleteRecord(MallGrouponRule ad) {
        if(ruleMapper.deleteByPrimaryKey(ad.getId())==1){
            return new MessageBean<>("成功",0,null);
        }
        return new MessageBean<>("失败",1,null);
    }

    @Override
    public MessageBean<ListDate<MallCoupon>> showCouponListByPage(int page, int limit, String content,String status,String name) {
        if(name!=null){
            name="%"+name+"%";}
        ArrayList<MallCoupon> list=couponMapper.queryCouponList(page-1,limit,content,status,name);
        ListDate<MallCoupon> listDate=new ListDate<>();
        listDate.setTotal(couponMapper.countNumber());
        listDate.setItems(list);
        return new MessageBean<ListDate<MallCoupon>>("成功",0,listDate);
    }

    @Override
    public MessageBean<ListDate<String>> showSingleCouponByPage(int page, int limit, String content, String name, String couponId) {
        ListDate<String> listDate=new ListDate<>();
        listDate.setTotal(0);
        ArrayList<String> list=new ArrayList<>();
        list.add("");
        listDate.setItems(list);
        return new MessageBean<ListDate<String>>("成功",0,listDate);
    }

    @Override
    public MessageBean<MallCoupon> readMallCouponInfo(String couponId) {
        MallCoupon coupon=couponMapper.selectByPrimaryKey(Integer.valueOf(couponId));
        return new MessageBean<>("成功",0,coupon);
    }

    @Override
    public MessageBean<MallCoupon> updateRecord(MallCoupon ad) {
        if(couponMapper.updateByPrimaryKeySelective(ad)==1){
            return new MessageBean<>("成功",0,ad);
        }
        return new MessageBean<>("失败",1,ad);
    }

    @Override
    public MessageBean<MallCoupon> addRecord(MallCoupon ad) {
        if(couponMapper.insert(ad)==1){
            return new MessageBean<>("成功",0,ad);
        }
        return new MessageBean<>("失败",1,ad);
    }

    @Override
    public MessageBean<String> deleteRecord(MallCoupon ad) {
        if(couponMapper.deleteByPrimaryKey(ad.getId())==1){
            return new MessageBean<>("成功",0,null);
        }
        return new MessageBean<>("失败",1,null);
    }

    @Override
    public MessageBean<ListDate<GrouponInfo>> showGrouponInfoListByPage(int page, int limit, String content, String name) {
        PageHelper.startPage(page,limit);
        if(content!=null){
            content="%"+content+"%";}
        if(name!=null){
            name="%"+name+"%";}
        ArrayList<GrouponInfo> list=grouponMapper.queryGrouponInfoList(content,name);
        PageInfo<GrouponInfo> pageInfo=new PageInfo<>(list);
        ListDate<GrouponInfo> listDate=new ListDate<>();
        listDate.setTotal(pageInfo.getTotal());
        listDate.setItems(list);
        return new MessageBean<ListDate<GrouponInfo>>("成功",0,listDate);
    }
}
