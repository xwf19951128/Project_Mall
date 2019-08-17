package com.cskaoyan.controller.goods;

import com.cskaoyan.bean.goods.Goods;
import com.cskaoyan.bean.goods.GoodsDataVo;
import com.cskaoyan.bean.goods.PageParams4Goods;
import com.cskaoyan.service.goods.GoodsService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    /*分页查询，模糊查询*/
    @RequestMapping("/list")
    public ResponseVo listPageGoods(PageParams4Goods pageParams4Goods, String goodsSn, String name){
/*        long total = goodsService.countTotalGoodsCount();
        if(total == 0){
            return ResponseUtil.fail(null, "总数为0", 0);
        }*/
        List<Goods> goodsList = goodsService.listPageGoods(pageParams4Goods, goodsSn, name);
        if(goodsList == null){
            return ResponseUtil.fail(null, "无商品信息", 0);
        }
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        long total = pageInfo.getTotal();
        GoodsDataVo<Goods> goodsDataVo = new GoodsDataVo<Goods>(total, goodsList);
        return ResponseUtil.success(goodsDataVo);
    }



}
