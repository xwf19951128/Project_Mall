package com.cskaoyan.service.wx.index;

import com.cskaoyan.bean.admin.goods.GoodsComment;
import com.cskaoyan.bean.admin.spread.MallTopic;
import com.cskaoyan.bean.wx.index.vo.WxCommentDate;
import com.cskaoyan.mapper.goods.GoodsCommentMapper;
import com.cskaoyan.mapper.spread.MallTopicMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 18:20 2019/8/22
 */
@Service
public class TopicDetailServiceImpl implements TopicDetailService{

    @Autowired
    MallTopicMapper mallTopicMapper;

    @Override
    public String selectGoodsByTopicId(int id) {
        return mallTopicMapper.selectGoodsByTopicId(id);
    }

    @Override
    public MallTopic selectTopicById(int id) {
        return mallTopicMapper.selectByPrimaryKey(id);
    }

    @Autowired
    GoodsCommentMapper goodsCommentMapper;

    @Override
    public List<GoodsComment> queryCommentsByValueIdAndPicture(Integer valueId, Integer type, Integer showType) {
        return goodsCommentMapper.queryCommentsByValueIdAndPicture(valueId,type,showType);
    }

    @Override
    public WxCommentDate<GoodsComment> getCommentsForWx(Integer page, Integer size, Integer valueId, Integer type) {

        Page<Object> objects = PageHelper.startPage(page, size);
        List<GoodsComment> goodsComments = goodsCommentMapper.getGoodsCommentByValueIdAndType(valueId, type);
        WxCommentDate<GoodsComment> goodsCommentWxCommentDate = new WxCommentDate<>();
        goodsCommentWxCommentDate.setCount((int) objects.getTotal());
        goodsCommentWxCommentDate.setCurrentPage(page);
        goodsCommentWxCommentDate.setData(goodsComments);
        return goodsCommentWxCommentDate;
    }

    @Override
    public List<MallTopic> getRelatedTopic(int id) {
        return mallTopicMapper.getRelatedTopic(id-3,id+3);
    }
}
