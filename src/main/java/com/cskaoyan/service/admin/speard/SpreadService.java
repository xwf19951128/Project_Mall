package com.cskaoyan.service.admin.speard;

import com.cskaoyan.bean.admin.spread.*;


public interface SpreadService {

    MessageBean<MallTopic> updateRecord(MallTopic ad);

    MessageBean<MallTopic> addRecord(MallTopic ad);

    MessageBean<String> deleteRecord(MallTopic ad);

    MessageBean<ListDate<MallTopic>> showTopicListByPage(int page, int limit, String content, String name);

    MessageBean<ListDate<MallAD>> showADListByPage(int page, int limit, String content, String name);

    MessageBean<MallAD> updateRecord(MallAD ad);

    MessageBean<MallAD> addRecord(MallAD ad);

    MessageBean<String> deleteRecord(MallAD ad);

    MessageBean<ListDate<MallGrouponRule>> showGrouponRuleListByPage(int page, int limit, String goodsId);

    MessageBean<MallGrouponRule> updateRecord(MallGrouponRule ad);

    MessageBean<MallGrouponRule> addRecord(MallGrouponRule ad);

    MessageBean<String> deleteRecord(MallGrouponRule ad);

    MessageBean<ListDate<MallCoupon>> showCouponListByPage(int page, int limit, String type, String status, String name);

    MessageBean<ListDate<String>> showSingleCouponByPage(int page, int limit, String content, String name, String couponId);

    MessageBean<MallCoupon> readMallCouponInfo(String couponId);

    MessageBean<MallCoupon> updateRecord(MallCoupon ad);

    MessageBean<MallCoupon> addRecord(MallCoupon ad);

    MessageBean<String> deleteRecord(MallCoupon ad);



    MessageBean<ListDate<GrouponInfo>> showGrouponInfoListByPage(int page, int limit, String id);
}
