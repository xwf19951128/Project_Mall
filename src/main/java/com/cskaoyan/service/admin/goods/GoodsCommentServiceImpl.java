package com.cskaoyan.service.admin.goods;

import com.cskaoyan.bean.admin.goods.GoodsComment;
import com.cskaoyan.bean.admin.goods.GoodsCommentExample;
import com.cskaoyan.bean.admin.goods.PageParams4Goods;
import com.cskaoyan.mapper.goods.GoodsCommentMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsCommentServiceImpl implements GoodsCommentService {

    @Autowired
    GoodsCommentMapper goodsCommentMapper;

    @Override
    public List<GoodsComment> listPageSearchGoodsComments(PageParams4Goods pageParams4Goods, Integer userId, Integer valueId) {
        GoodsCommentExample goodsCommentExample = new GoodsCommentExample();
        if(userId == null && valueId == null){
            goodsCommentExample.createCriteria().andIdIsNotNull();
        }else if(userId != null && valueId == null){
            goodsCommentExample.createCriteria().andUserIdEqualTo(userId);
        }else if(valueId != null && userId == null){
            goodsCommentExample.createCriteria().andValueIdEqualTo(valueId);
        }else if(valueId != null && userId != null){
            goodsCommentExample.createCriteria().andUserIdEqualTo(userId).andValueIdEqualTo(valueId);
        }
        String sort = pageParams4Goods.getSort();
        String order = pageParams4Goods.getOrder();
        PageHelper.startPage(pageParams4Goods.getPage(), pageParams4Goods.getLimit(), sort + " " + order);
        return goodsCommentMapper.selectByExample(goodsCommentExample);
    }
}
