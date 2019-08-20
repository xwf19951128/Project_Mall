package com.cskaoyan.controller.admin.goods;

import com.cskaoyan.bean.admin.goods.*;
import com.cskaoyan.bean.admin.goods.*;
import com.cskaoyan.service.admin.goods.GoodsAttributeService;
import com.cskaoyan.service.admin.goods.GoodsProductService;
import com.cskaoyan.service.admin.goods.GoodsService;
import com.cskaoyan.service.admin.goods.GoodsSpecificationService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsAttributeService goodsAttributeService;

    @Autowired
    GoodsProductService goodsProductService;

    @Autowired
    GoodsSpecificationService goodsSpecificationService;

    /*分页查询*/
    @RequestMapping("/list")
    public ResponseVo listPageGoods(PageParams4Goods pageParams4Goods){
/*        long total = goodsService.countTotalGoodsCount();
        if(total == 0){
            return ResponseUtil.fail(null, "总数为0", 0);
        }*/
        List<Goods> goodsList = goodsService.listPageGoods(pageParams4Goods);
        if(goodsList == null){
            return ResponseUtil.fail(null, "无商品信息", 0);
        }
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        long total = pageInfo.getTotal();
        GoodsDataVo<Goods> goodsDataVo = new GoodsDataVo<Goods>(total, goodsList);
        return ResponseUtil.success(goodsDataVo);
    }

    /*模糊查询*/
    @RequestMapping(value = "/list", params = {"goodsSn"})
    public ResponseVo listPageGoodsByGoodsSn(PageParams4Goods pageParams4Goods, String goodsSn){
        List<Goods> goodsList = goodsService.listPageGoodsByGoodsSn(pageParams4Goods, goodsSn);
        if(goodsList == null){
            return ResponseUtil.fail(null, "无商品信息", 0);
        }
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        long total = pageInfo.getTotal();
        GoodsDataVo<Goods> goodsDataVo = new GoodsDataVo<Goods>(total, goodsList);
//        System.out.println("goodsSn");
        return ResponseUtil.success(goodsDataVo);
    }


    /*模糊查询*/
    @RequestMapping(value = "/list", params = {"name"})
    public ResponseVo listPageGoodsByName(PageParams4Goods pageParams4Goods, String name){
        List<Goods> goodsList = goodsService.listPageGoodsByName(pageParams4Goods, name);
        if(goodsList == null){
            return ResponseUtil.fail(null, "无商品信息", 0);
        }
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        long total = pageInfo.getTotal();
        GoodsDataVo<Goods> goodsDataVo = new GoodsDataVo<Goods>(total, goodsList);
//        System.out.println("name");
        return ResponseUtil.success(goodsDataVo);
    }


    /*模糊查询*/
    @RequestMapping(value = "/list", params = {"goodsSn", "name"})
    public ResponseVo listPageGoodsByGoodsSnAndName(PageParams4Goods pageParams4Goods, String goodsSn, String name){
        List<Goods> goodsList = goodsService.listPageGoodsByGoodsSnAndName(pageParams4Goods, goodsSn, name);
        if(goodsList == null){
            return ResponseUtil.fail(null, "无商品信息", 0);
        }
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        long total = pageInfo.getTotal();
        GoodsDataVo<Goods> goodsDataVo = new GoodsDataVo<Goods>(total, goodsList);
//        System.out.println("goodsSn name");
        return ResponseUtil.success(goodsDataVo);
    }

    /*编辑回显*/
    @RequestMapping("/detail")
    public ResponseVo getGoodsDetail(int id){
        HashMap<String, Object> data = new HashMap<>(5);
        List<GoodsAttribute> goodsAttributeList = goodsAttributeService.listGoodsAttributesByGoodsId(id);
        Goods goods = goodsService.getSingleGoodsById(id);
        List<Integer> categoryIds = Arrays.asList(1005000, goods.getCategoryId());
        List<GoodsProduct> goodsProductList = goodsProductService.listGoodsProductsByGoodsId(id);
        List<GoodsSpecification> goodsSpecificationList = goodsSpecificationService.listGoodsSpecificationsByGoodsId(id);
        data.put("attributes", goodsAttributeList);
        data.put("categoryIds", categoryIds);
        data.put("goods", goods);
        data.put("products", goodsProductList);
        data.put("specifications", goodsSpecificationList);
        return ResponseUtil.success(data);
    }

    /*添加商品*/
/*    @RequestMapping("/create")
    public ResponseVo insertGoods(@RequestBody GoodsAttribute[] goodsAttributeArray,
                                  @RequestBody Goods goods,
                                  @RequestBody GoodsProduct[] goodsProductArray,
                                  @RequestBody GoodsSpecification[] specificationArray){
        return null;
    }*/

    /*删除商品*/
    @RequestMapping("/delete")
    public ResponseVo deleteSingleGoods(@RequestBody Goods goods){
        Integer goodsId = goods.getId();
        int deleteResult = goodsService.deleteSingleGoodsById(goodsId);
        if(deleteResult != 1){
            return ResponseUtil.fail(null, "删除失败", 4);
        }
        return ResponseUtil.success();
    }



}
