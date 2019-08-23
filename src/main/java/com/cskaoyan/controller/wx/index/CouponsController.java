package com.cskaoyan.controller.wx.index;


import com.cskaoyan.bean.admin.spread.MallCoupon;
import com.cskaoyan.bean.admin.spread.UserCouponExample;
import com.cskaoyan.bean.wx.coreservice.UserCoupon;
import com.cskaoyan.bean.wx.index.vo.WxResponseVo;
import com.cskaoyan.bean.wx.login.WxUser;
import com.cskaoyan.mapper.coreservice.UserCouponMapper;
import com.cskaoyan.mapper.spread.MallCouponMapper;
import com.cskaoyan.util.ResponseVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: XiaoLei
 * @Date Created in 13:16 2019/8/23
 */
@RestController
@Component
public class CouponsController {

    @Autowired
    UserCouponMapper userCouponMapper;

    @Autowired
    MallCouponMapper mallCouponMapper;

    //传入的是一个json对象，而不是一个字符串,接收对象用jsonobject
//    @RequestMapping(value = "/wx/coupon/receive" ,method = RequestMethod.POST)
    public WxResponseVo receiveCoupon(@RequestBody Map jsonObject) throws JSONException {

        int couponId = (int) jsonObject.get("couponId");

        WxResponseVo wxResponseVo = new WxResponseVo();
        //获得UserId以后，判断优惠券code是否有效，如果有效往coupon_user里塞
        Subject subject = SecurityUtils.getSubject();
        //获取认证后的用户信息，通过Realm进行封装的
        WxUser user = (WxUser) subject.getPrincipal();
        if(user==null){
            wxResponseVo.setErrmsg("请登录");
            wxResponseVo.setErrno(501);
            return wxResponseVo;
        }else{
            //查出该用户是否有该优惠券
            UserCouponExample userCouponExample = new UserCouponExample();
            UserCouponExample.Criteria criteria = userCouponExample.createCriteria();
            criteria.andCouponIdEqualTo(couponId).andUserIdEqualTo(user.getId());
            List<UserCoupon> userCoupons =userCouponMapper.selectByExample(userCouponExample);
            if(userCoupons.size()!=0){
                wxResponseVo.setErrno(740);
                wxResponseVo.setErrmsg("优惠券已经领取过");
                return wxResponseVo;
            }
            //找出该优惠券
            MallCoupon mallCoupon = mallCouponMapper.selectByPrimaryKey(couponId);
            //然后把该优惠券塞进用户
            UserCoupon userCoupon = new UserCoupon();
            userCoupon.setUserId(user.getId());
            userCoupon.setStatus((short) 0);
            userCoupon.setCouponId(mallCoupon.getId());
            userCoupon.setStartTime(mallCoupon.getStartTime());
            userCoupon.setEndTime(mallCoupon.getEndTime());
            userCoupon.setAddTime(new Date());
            userCouponMapper.insert(userCoupon);

            wxResponseVo.setErrmsg("成功");
            wxResponseVo.setErrno(0);
            return wxResponseVo;
        }
    }

}
