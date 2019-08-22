package com.cskaoyan.controller.wx.index;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.mall.brand.Brand;
import com.cskaoyan.bean.admin.mall.brand.BrandExample;
import com.cskaoyan.bean.admin.mall.category.Category;
import com.cskaoyan.bean.admin.mall.category.CategoryExample;
import com.cskaoyan.bean.admin.spread.*;
import com.cskaoyan.bean.wx.index.GroupOnList;
import com.cskaoyan.bean.wx.index.FloorGoodList;
import com.cskaoyan.bean.wx.index.IndexList;
import com.cskaoyan.mapper.goods.GoodsMapper;
import com.cskaoyan.mapper.mall.BrandMapper;
import com.cskaoyan.mapper.mall.CategoryMapper;
import com.cskaoyan.mapper.spread.MallADMapper;
import com.cskaoyan.mapper.spread.MallCouponMapper;
import com.cskaoyan.mapper.spread.MallGrouponRuleMapper;
import com.cskaoyan.mapper.spread.MallTopicMapper;

import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * @Author: XiaoLei
 * @Date Created in 10:38 2019/8/21
 *
 */
@RestController
public class IndexController {

    @Autowired
    MallADMapper mallADMapper;

    @Autowired
    BrandMapper brandMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    MallCouponMapper mallCouponMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    MallTopicMapper mallTopicMapper;

    @Autowired
    MallGrouponRuleMapper mallGrouponRuleMapper;

    @RequestMapping("wx/home/index")
    public ResponseVo index(){

        ResponseVo responseVo = new ResponseVo();
        IndexList indexList = new IndexList();

        //查询广告横栏
        List<MallAD> mallADS= mallADMapper.selectByExample(new MallADExample());
        indexList.setBanner(mallADS);
//        List<MallAD> mallADS= mallADMapper.selectMallAdForIndex();
//        indexList.setMallADS(mallADS);

        //品牌制造商直供 ，对应brand,要随机生成四张图,修剪成四张
        List<Brand> brandList = brandMapper.selectByExample(new BrandExample());
        Random random = new Random();
        int i = random.nextInt(brandList.size() - 4);
        List<Brand> brandList1 = brandList.subList(i, i+4);
        indexList.setBrandList(brandList1);

        //渠道，也就是分类，category,根据前端，pid为0时，显示该目录
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andPidEqualTo(0);
        List<Category> categories =categoryMapper.selectByExample(categoryExample);
        indexList.setChannel(categories);

        ///优惠券列表，对应MallCoupon,找type为0的
//    逆向工程竟然失败了，只能自己写
//        List<MallCoupon> mallCoupons =mallCouponMapper.selectByExample(new MallCouponExample());
        List<MallCoupon> mallCoupons =mallCouponMapper.selectMallCouponForIndex();
        int j =random.nextInt(mallCoupons.size()-2);
        List<MallCoupon> mallCoupons1 =mallCoupons.subList(j,j+2);
        indexList.setCouponList(mallCoupons1);

        //因为热门商品和最新商品没有使用逆向工程生成
        // 为避免冲突，跳过service层，直接调用goodsmapper，在里面写逻辑

        //热门商品展示,随机展示6个
        List<Goods> hotGoodList =goodsMapper.selectHotGoods();
        int a =random.nextInt(hotGoodList.size() - 6);
        List<Goods> hotgoodsList2 = hotGoodList.subList(a, a + 6);
        indexList.setHotGoodsList(hotgoodsList2);

        //最新商品展示
        List<Goods> newGoodList =goodsMapper.selectNewGoods();
        int b =random.nextInt(newGoodList.size()-6);
        List<Goods> newgoodsList2 =newGoodList.subList(b,b+6);
        indexList.setNewGoodsList(newgoodsList2);

        //专题精选
        MallTopicExample mallTopicExample = new MallTopicExample();

        List<MallTopic> mallTopics = mallTopicMapper.selectByExample(mallTopicExample);
        int c =random.nextInt(mallTopics.size()-4);
        List<MallTopic> mallTopics1 =mallTopics.subList(c,c+4);
        indexList.setTopicList(mallTopics1);

        //团购专区，最低价，有number和价格，去rule查询
        List<GroupOnList> groupOnLists = mallGrouponRuleMapper.getFloorList();
        indexList.setGroupOnList(groupOnLists);

        //最底部的商品列表。带着很多商品，新建一个javabean
        List<FloorGoodList> floorGoodLists =categoryMapper.getGroupList();
        indexList.setFloorGoodsList(floorGoodLists);

        responseVo.setData(indexList);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;
    }
}
