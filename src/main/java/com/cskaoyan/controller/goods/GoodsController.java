package com.cskaoyan.controller.goods;

import com.cskaoyan.bean.goods.Goods;
import com.cskaoyan.bean.goods.GoodsDataVo;
import com.cskaoyan.bean.goods.GoodsResponseVo;
import com.cskaoyan.bean.goods.PageParams4Goods;
import com.cskaoyan.service.goods.GoodsService;
import com.cskaoyan.utils.ResponseUtil;
import com.cskaoyan.utils.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/list")
    public ResponseVo listPageGoods(PageParams4Goods pageParams4Goods){
        long total = goodsService.countTotalGoodsCount();
        if(total == 0){
//            return new GoodsResponseVo<GoodsDataVo<Goods>>(0, null, "总数为0");
            return ResponseUtil.fail(null, "总数为0", 0);
        }
        List<Goods> goodsList = goodsService.listPageGoods(pageParams4Goods);
        if(goodsList == null){
//            return new GoodsResponseVo<GoodsDataVo<Goods>>(0, null, "无商品信息");
            return ResponseUtil.fail(null, "无商品信息", 0);
        }
        GoodsDataVo<Goods> goodsDataVo = new GoodsDataVo<Goods>(total, goodsList);
//        return new GoodsResponseVo<>(0, goodsDataVo, "成功");
        return ResponseUtil.success(goodsDataVo);
    }



}
