package com.cskaoyan.controller.wx.index;

import com.cskaoyan.bean.admin.goods.*;
import com.cskaoyan.bean.admin.mall.brand.Brand;
import com.cskaoyan.bean.admin.mall.category.Category;
import com.cskaoyan.bean.admin.mall.category.CategoryExample;
import com.cskaoyan.bean.admin.mall.issue.Issue;
import com.cskaoyan.bean.admin.spread.MallGrouponRule;
import com.cskaoyan.bean.admin.spread.MallGrouponRuleExample;
import com.cskaoyan.bean.admin.userManage.User;
import com.cskaoyan.bean.wx.coreservice.Collect;
import com.cskaoyan.bean.wx.coreservice.CollectExample;
import com.cskaoyan.bean.wx.coreservice.Footprint;

import com.cskaoyan.bean.wx.coreservice.FootprintExample;
import com.cskaoyan.bean.wx.goods.ResponseGoodVo;
import com.cskaoyan.bean.wx.index.GoodsCount;
import com.cskaoyan.bean.wx.login.WxUser;
import com.cskaoyan.mapper.coreservice.CollectMapper;
import com.cskaoyan.mapper.coreservice.FootprintMapper;
import com.cskaoyan.mapper.goods.*;
import com.cskaoyan.mapper.login.WxUserMapper;
import com.cskaoyan.mapper.mall.BrandMapper;
import com.cskaoyan.mapper.mall.CategoryMapper;
import com.cskaoyan.mapper.mall.IssueMapper;
import com.cskaoyan.mapper.spread.MallGrouponRuleMapper;
import com.cskaoyan.service.wx.index.GoodListService;
import com.cskaoyan.util.ResponseVo;
import com.cskaoyan.util.wx.UserTokenManager;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HEAD;

import java.util.*;


/**
 * @Author: XiaoLei
 * @Date Created in 11:35 2019/8/22
 * 一开始，避免冲突，本例跳过三层架构，直接调用mapper
 */
@RestController
public class GoodListController {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    BrandMapper brandMapper;


    //当含有brandId便签时，才能进入该方法
    @RequestMapping(value = "/wx/goods/list",params = {"brandId"})
    public ResponseVo getGoodsList(
            @RequestParam(value = "brandId", required = false) int brandId,
            @RequestParam(value = "page",required = false) int page,
            @RequestParam(value = "size",required = false) int size){
        ResponseVo responseVo = new ResponseVo();
        ResponseGoodVo responseGoodVo = new ResponseGoodVo();

       List<Brand> brands =brandMapper.getBrandListById(brandId);
       List<Goods> goodsList =goodsMapper.selectGoodsByBrandId(brandId);

        PageHelper pageHelper = new PageHelper();
        Page<Object> pageResult = pageHelper.startPage(page, size);

        responseGoodVo.setCount(pageResult.size());
        responseGoodVo.setGoodsList(goodsList);
        responseGoodVo.setFilterCategoryList(brands);

        responseVo.setData(responseGoodVo);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;
    }

    //通过一个一级目录的id，找出一级目录的所有信息，放入parentcategory
    //在通过一个目录的id，找出二级目录的所有id，同时，找出二级目录的目录信息，放入bro目录
    // 同时，根据一级目录的id找出的二级目录的第一个作为当前目录
    @RequestMapping(value="/wx/goods/category")
    public ResponseVo getCategory(@Param("id") Integer id){
        ResponseVo responseVo = new ResponseVo();
        //一、找出parentcategroy
//        Category parentcategroy =categoryMapper.selectByPrimaryKey(id);
        //二、找出broid;
        //1.找出二级目录id
//        List<Integer> SecondCategoryId = categoryMapper.SelectSecond(id);
//        //2.根据二级目录id，找出二级目录的所有详情
//        ArrayList<Category> arrayList = new ArrayList();
//        for (int secondId : SecondCategoryId) {
//            Category category = categoryMapper.selectByPrimaryKey(secondId);
//            arrayList.add(category);
//        }
        //三、找出当前id,当前id就是broid的第一个id

        Category curryCategory=categoryMapper.selectByPrimaryKey(id);
        //如果 currentCategory 是一级类目，将其第一个二级类目作为 currentCategory
        if (curryCategory.getPid() == 0) {
            List<Category> categories = categoryMapper.queryAllCategoryByPid(id);
            if (categories.size() > 0) {
                curryCategory = categories.get(0);
            }
        }
        List<Category> brotherCategory = new ArrayList<>();
        Category parentCategory = null;
        if (curryCategory != null) {
            //查询该 categoryId 的同级 category
            brotherCategory = categoryMapper.queryAllCategoryByPid(curryCategory.getPid());
            //查询该 categoryId 的父级 category
            parentCategory = categoryMapper.selectByPrimaryKey(curryCategory.getPid());
        }

        HashMap hashMap = new HashMap();
        hashMap.put("brotherCategory",brotherCategory);
        hashMap.put("currentCategory",curryCategory);
        hashMap.put("parentCategory",parentCategory);
        responseVo.setData(hashMap);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }

    //根据categoryId进入该方法
    @RequestMapping(value = "/wx/goods/list",params = {"categoryId","!keyword"})
    public ResponseVo getGoodList2(
            @RequestParam(value = "categoryId", required = false) int categoryId,
            @RequestParam(value = "page",required = false) int page,
            @RequestParam(value = "size",required = false) int size){

//        List<Integer> SecondCategoryId = categoryMapper.SelectSecond(categoryId);

//        //找出二级目录的详情
//        ArrayList<Category> filterCategoryList = new ArrayList();
//        for (int secondId : SecondCategoryId) {
//            Category category = categoryMapper.selectByPrimaryKey(secondId);
//            filterCategoryList.add(category);
//        }


        //好像不是找出二级目录的详情，应该找的是所有的二级目录
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andLevelEqualTo("L2");
        ArrayList<Category> filterCategoryList = (ArrayList<Category>) categoryMapper.selectByExample(categoryExample);

        //传入的categoryid是二级目录id，
//        List<Category> filterCategoryList = categoryMapper.selectCategorysById(SecondCategoryId);
        //根据二级目录的id找到所有goods
        List<Goods> goodsList = goodsMapper.selectGoodsByCategoryId(categoryId);


        PageHelper pageHelper = new PageHelper();
        Page<Object> pageresulut = pageHelper.startPage(page, size);

        ResponseGoodVo responseGoodVo = new ResponseGoodVo();
        responseGoodVo.setCount(pageresulut.size());
        responseGoodVo.setGoodsList(goodsList);
        responseGoodVo.setFilterCategoryList(filterCategoryList);

        ResponseVo responseVo = new ResponseVo();
        responseVo.setData(responseGoodVo);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;

    }

    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;

    @Autowired
    GoodsCommentMapper goodsCommentMapper;

    @Autowired
    WxUserMapper wxUserMapper;

    @Autowired
    MallGrouponRuleMapper mallGrouponRuleMapper;

    @Autowired
    IssueMapper issueMapper;

    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Autowired
    CollectMapper collectMapper;

    @Autowired
    FootprintMapper footprintMapper;

    @RequestMapping("/wx/goods/detail")
    public ResponseVo getGoodsDetail(@Param("id") Integer id, HttpServletRequest request){

        //获取用户
        String tolen= request.getHeader("X-Litemall-Token");
        Integer userId = UserTokenManager.getUserId(tolen);
        WxUser user = wxUserMapper.selectByPrimaryKey(userId);

        //根据good id找出所需的东西
        //1.找attribute
        List<GoodsAttribute> goodsAttributeList =goodsAttributeMapper.selectGoodsAttributeByGoodId(id);
        //2.获得info
        Goods goods =goodsMapper.getSingleGoodsById(id);
        //3.获得brand
        Brand brand = new Brand();
        if(goods.getBrandId()!= 0) {
            brand = brandMapper.selectByPrimaryKey(goods.getBrandId());
        }
        //4.获得comment
        GoodsCommentExample goodsCommentExample = new GoodsCommentExample();
        GoodsCommentExample.Criteria criteria = goodsCommentExample.createCriteria();
        criteria.andValueIdEqualTo(id);

        List<GoodsComment> goodsComment = goodsCommentMapper.selectByExample(goodsCommentExample);
        List<HashMap> commentVo = new ArrayList<HashMap>();
        HashMap<String, Object> commentsVo = new HashMap<String, Object>();

        for (GoodsComment comment : goodsComment) {
            HashMap<String, Object> c = new HashMap<>();
                    c.put("id", comment.getId());
                    c.put("addTime", comment.getAddTime());
                    c.put("content", comment.getContent());
                    WxUser user1 = wxUserMapper.selectByPrimaryKey(comment.getUserId());
                    c.put("nickname", user1 == null ? "" : user1.getNickname());
                    c.put("avatar", user1 == null ? "" : user1.getAvatar());
                    c.put("picList", comment.getPicUrls());
                    commentVo.add(c);
        }
        commentsVo.put("count",goodsComment.size());
        if(goodsComment.size()>3){
            commentsVo.put("data",commentVo.subList(0,2));
        }else if (goodsComment.size()>0){
            commentsVo.put("data",commentVo);
        }else {
            commentsVo.put("data",null);
        }

        //5.grouprule,团购信息
        MallGrouponRuleExample mallGrouponRuleExample = new MallGrouponRuleExample();
        MallGrouponRuleExample.Criteria criteria1 = mallGrouponRuleExample.createCriteria();
        criteria1.andGoodsIdEqualTo(id);
        List<MallGrouponRule> grouponRules =mallGrouponRuleMapper.selectByExample(mallGrouponRuleExample);

        //6.issue
        List<Issue> issues = issueMapper.getIssueList();
        //7.produceList
        List<GoodsProduct> goodsProducts =goodsProductMapper.listGoodsProductsByGoodsId(id);
        //8.sharImage
        String picUrl = goods.getPicUrl();
        //9.specificationList
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria criteria3 = goodsSpecificationExample.createCriteria().andGoodsIdEqualTo(id);
        List<GoodsSpecification> goodsSpecifications =goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
        ArrayList<Map> goodsSpecificationsVo = new ArrayList<>();
        for (GoodsSpecification goodsSpecification : goodsSpecifications) {
            HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("name","规格");
            objectObjectHashMap.put("valueList",goodsSpecifications);
            goodsSpecificationsVo.add(objectObjectHashMap);
        }

        //10。userHasCollect
        int userHasCollect =0;
        if(user!=null){
          List<Collect> userCollect =collectMapper.selectCollectByUserId(user.getId());
            if(userCollect.size() != 0){
                userHasCollect = userCollect.get(0).getType();
            }else {
                userHasCollect = 0;
            }

        }
        //在点击商品详情的时候，添加浏览记录
        if(user!=null){
            //记录用户足迹:用户id，浏览商品的id，插入到footprint表
//            FootprintExample footprintExample = new FootprintExample();
//            FootprintExample.Criteria criteria2 = footprintExample.createCriteria();
//            criteria2.andUserIdEqualTo(user.getId());
//            criteria2.andGoodsIdEqualTo(id);
            //查看表中是否有该记录
//            long count = footprintMapper.countByExample(footprintExample);
//            if(count==0){
                Footprint footprint = new Footprint();
                footprint.setGoodsId(id);
                footprint.setUserId(userId);
                footprint.setAddTime(new Date());
                footprint.setUpdateTime(new Date());
                footprint.setDeleted(false);
                footprintMapper.insert(footprint);
//            }
        }

        HashMap hashMap = new HashMap();
        hashMap.put("attribute",goodsAttributeList);
        hashMap.put("info",goods);
        hashMap.put("brand",brand);
        hashMap.put("comment",commentsVo);
        hashMap.put("groupon",grouponRules);
        hashMap.put("issue",issues);
        hashMap.put("productList",goodsProducts);
        hashMap.put("shareImage",picUrl);
        hashMap.put("specificationList",goodsSpecificationsVo);
        hashMap.put("userHasCollect",userHasCollect);

        ResponseVo responseVo = new ResponseVo();
        responseVo.setData(hashMap);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;
    }
    //先根据goodId，找出categoryid，在根据categoryid找出所有的good，限制四个
    @RequestMapping("/wx/goods/related")
    public ResponseVo getRelatedGoods(Integer id){
       Goods goods =goodsMapper.getSingleGoodsById(id);
       List<Goods> goodsList = goodsMapper.selectGoodsByCategoryId(goods.getCategoryId());
        List<Goods> goodsList1=null;
       if(goodsList.size()>4){
           goodsList1 =goodsList.subList(0,4);
       }else{
           goodsList1=goodsList;
       }
        HashMap hashMap = new HashMap();
       hashMap.put("goodsList",goodsList1);
        ResponseVo responseVo = new ResponseVo();
        responseVo.setData(hashMap);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }




    @Autowired
    GoodListService goodListService;

    @RequestMapping("/wx/goods/count")
    public ResponseVo queryGoodsCount(){
        ResponseVo responseVo = new ResponseVo();
        List<Goods> goods =goodListService.selectAllCountGoods();
        int i =goods.size();
        //需要以javabean传出去
        GoodsCount goodsCount = new GoodsCount(i);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        responseVo.setData(goodsCount);
        return responseVo;
    }

}
