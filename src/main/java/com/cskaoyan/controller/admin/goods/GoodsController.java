package com.cskaoyan.controller.admin.goods;

import com.cskaoyan.bean.admin.goods.*;
import com.cskaoyan.bean.admin.mall.brand.Brand;
import com.cskaoyan.bean.admin.mall.category.CategoryFirstClass;
import com.cskaoyan.service.admin.goods.GoodsAttributeService;
import com.cskaoyan.service.admin.goods.GoodsProductService;
import com.cskaoyan.service.admin.goods.GoodsService;
import com.cskaoyan.service.admin.goods.GoodsSpecificationService;
import com.cskaoyan.service.admin.mall.BrandService;
import com.cskaoyan.service.admin.mall.CategoryService;
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
import java.util.Map;

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

    @Autowired
    CategoryService categoryService;

    @Autowired
    BrandService brandService;

    /*分页查询*/
    @RequestMapping("/list")
    public ResponseVo listPageGoods(PageParams4Goods pageParams4Goods){
/*        long total = goodsService.countTotalGoodsCount();
        if(total == 0){
            return ResponseUtil.fail(null, "总数为0", 0);
        }*/
        List<Goods> goodsList = goodsService.listPageGoods(pageParams4Goods);
        if(goodsList == null){
            return ResponseUtil.fail(null, "查询失败", 502);
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
            return ResponseUtil.fail(null, "查询失败", 502);
        }
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        long total = pageInfo.getTotal();
        GoodsDataVo<Goods> goodsDataVo = new GoodsDataVo<Goods>(total, goodsList);
        return ResponseUtil.success(goodsDataVo);
    }


    /*模糊查询*/
    @RequestMapping(value = "/list", params = {"name"})
    public ResponseVo listPageGoodsByName(PageParams4Goods pageParams4Goods, String name){
        List<Goods> goodsList = goodsService.listPageGoodsByName(pageParams4Goods, name);
        if(goodsList == null){
            return ResponseUtil.fail(null, "查询失败", 502);
        }
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        long total = pageInfo.getTotal();
        GoodsDataVo<Goods> goodsDataVo = new GoodsDataVo<Goods>(total, goodsList);
        return ResponseUtil.success(goodsDataVo);
    }


    /*模糊查询*/
    @RequestMapping(value = "/list", params = {"goodsSn", "name"})
    public ResponseVo listPageGoodsByGoodsSnAndName(PageParams4Goods pageParams4Goods, String goodsSn, String name){
        List<Goods> goodsList = goodsService.listPageGoodsByGoodsSnAndName(pageParams4Goods, goodsSn, name);
        if(goodsList == null){
            return ResponseUtil.fail(null, "查询失败", 502);
        }
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        long total = pageInfo.getTotal();
        GoodsDataVo<Goods> goodsDataVo = new GoodsDataVo<Goods>(total, goodsList);
        return ResponseUtil.success(goodsDataVo);
    }

    /*编辑回显*/
    @RequestMapping("/detail")
    public ResponseVo getGoodsDetail(int id){
        HashMap<String, Object> data = new HashMap<>(5);
        List<GoodsAttribute> goodsAttributeList = goodsAttributeService.listGoodsAttributesByGoodsId(id);
        if(goodsAttributeList == null){
            return ResponseUtil.fail(null, "goodsAttributeList查询失败", 502);
        }
        Goods goods = goodsService.getSingleGoodsById(id);
        List<Integer> categoryIds = Arrays.asList(1005000, goods.getCategoryId());
        if(categoryIds == null){
            return ResponseUtil.fail(null, "categoryIds查询失败", 502);
        }
        List<GoodsProduct> goodsProductList = goodsProductService.listGoodsProductsByGoodsId(id);
        if(goodsProductList == null){
            return ResponseUtil.fail(null, "goodsProductList查询失败", 502);
        }
        List<GoodsSpecification> goodsSpecificationList = goodsSpecificationService.listGoodsSpecificationsByGoodsId(id);
        if(goodsSpecificationList == null){
            return ResponseUtil.fail(null, "goodsSpecificationList查询失败", 502);
        }
        data.put("attributes", goodsAttributeList);
        data.put("categoryIds", categoryIds);
        data.put("goods", goods);
        data.put("products", goodsProductList);
        data.put("specifications", goodsSpecificationList);
        return ResponseUtil.success(data);
    }


    /**
     * 添加商品时分类、品牌商回显
     * @return
     */
    @RequestMapping("/catAndBrand")
    public ResponseVo getCatAndBrand(){
        HashMap<String, Object> data = new HashMap<>(2);
        List<CategoryFirstClass> categoryList = categoryService.getCategory();
        if(categoryList == null){
            return ResponseUtil.fail(null, "categoryList查询失败", 502);
        }
        List<Brand> brandList = brandService.getBrandList(null, "");
        if(brandList == null){
            return ResponseUtil.fail(null, "brandList查询失败", 502);
        }
        data.put("categoryList", categoryList);
        data.put("brandList", brandList);
        return ResponseUtil.success(data);
    }



    /*添加商品*/
    @RequestMapping("/create")
    public ResponseVo insertGoods(@RequestBody Map<String, Object> map){
        Map<String, Object> goodsMap = (Map<String, Object>) map.get("goods");
        int result1 = goodsService.insertSingleGoods(goodsMap);
        Integer lastInsertGoodsId = (Integer)goodsMap.get("id");

        List<GoodsAttribute> goodsAttributeList = (List<GoodsAttribute>) map.get("attributes");
        int result2 = goodsAttributeService.insertGoodsAttributes(goodsAttributeList, lastInsertGoodsId);
//        System.out.println("result2 = " + result2);
        List<Map<String, Object>> goodsProductMapList = (List<Map<String, Object>>) map.get("products");
        int result3 = goodsProductService.insertGoodsProduct(goodsProductMapList, lastInsertGoodsId);
//        System.out.println("result3 = " + result3);
        List<Map<String, Object>> goodsSpecificationMapList = (List<Map<String, Object>>) map.get("specifications");
        int result4 = goodsSpecificationService.insertSpecifications(goodsSpecificationMapList, lastInsertGoodsId);
        if(result1 == 0 || result2  == 0 || result3  == 0 || result4  == 0) {
            return ResponseUtil.fail(null, "添加失败", 502);
        }
        return ResponseUtil.success();
    }

    /**
     * 编辑商品
     * TO DO
     * @param map
     * @return
     */
    @RequestMapping("/update")
    public ResponseVo updateSingleGoods(@RequestBody Map<String, Object> map){
        Map<String, Object> goodsMap = (Map<String, Object>) map.get("goods");
        int result1 = goodsService.updateSingleGoods(goodsMap);
        Integer lastInsertGoodsId = (Integer)goodsMap.get("id");

        List<GoodsAttribute> goodsAttributeList = (List<GoodsAttribute>) map.get("attributes");
        int result2 = goodsAttributeService.insertGoodsAttributes(goodsAttributeList, lastInsertGoodsId);
//        System.out.println("result2 = " + result2);
        List<Map<String, Object>> goodsProductMapList = (List<Map<String, Object>>) map.get("products");
        int result3 = goodsProductService.insertGoodsProduct(goodsProductMapList, lastInsertGoodsId);
//        System.out.println("result3 = " + result3);

        List<Map<String, Object>> goodsSpecificationMapList = (List<Map<String, Object>>) map.get("specifications");
        int result4 = goodsSpecificationService.insertSpecifications(goodsSpecificationMapList, lastInsertGoodsId);
        if(result1 != 0 && result2 != 0 && result3 != 0 && result4 != 0) {
            return ResponseUtil.success();
        }
        return ResponseUtil.fail(null, "添加失败", 1);
    }



    /*删除商品*/
    @RequestMapping("/delete")
    public ResponseVo deleteSingleGoods(@RequestBody Goods goods){
        Integer goodsId = goods.getId();
        int deleteResult = goodsService.deleteSingleGoodsById(goodsId);
        if(deleteResult == 0){
            return ResponseUtil.fail(null, "删除失败", 502);
        }
        return ResponseUtil.success();
    }



}
