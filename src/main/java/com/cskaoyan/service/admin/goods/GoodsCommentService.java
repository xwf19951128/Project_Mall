package com.cskaoyan.service.admin.goods;

import com.cskaoyan.bean.admin.goods.GoodsComment;
import com.cskaoyan.bean.admin.goods.PageParams4Goods;

import java.util.List;

public interface GoodsCommentService {
    List<GoodsComment> listPageSearchGoodsComments(PageParams4Goods pageParams4Goods, Integer userId, Integer valueId);

    int deleteGoodsCommentById(Integer id);

}
