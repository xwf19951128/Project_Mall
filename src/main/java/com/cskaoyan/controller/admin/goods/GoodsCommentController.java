package com.cskaoyan.controller.admin.goods;

import com.cskaoyan.bean.admin.goods.GoodsComment;
import com.cskaoyan.bean.admin.goods.PageParams4Goods;
import com.cskaoyan.service.admin.goods.GoodsCommentService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/comment")
public class GoodsCommentController {

    @Autowired
    GoodsCommentService goodsCommentService;

    /*分页查询，包括模糊查询*/
    @RequestMapping("/list")
    public ResponseVo listPageGoods(PageParams4Goods pageParams4Goods, Integer userId, Integer valueId){
        Map<Object, Object> data = new HashMap<>(2);
        List<GoodsComment> goodsCommentList = goodsCommentService.listPageSearchGoodsComments(pageParams4Goods, userId, valueId);
        if(goodsCommentList == null){
            return ResponseUtil.fail(null, "goodsCommentList查询失败", 502);
        }
        PageInfo<GoodsComment> pageInfo = new PageInfo<>(goodsCommentList);
        long total = pageInfo.getTotal();
        data.put("items", goodsCommentList);
        data.put("total", total);
        return ResponseUtil.success(data);
    }

    @RequestMapping("/delete")
    public ResponseVo deleteGoodsComment(@RequestBody GoodsComment goodsComment){
        Integer id = goodsComment.getId();
        int deleteResult = goodsCommentService.deleteGoodsCommentById(id);
        if(deleteResult == 0){
            return ResponseUtil.fail("null", "删除失败", 502);
        }
        return ResponseUtil.success();
    }
}
