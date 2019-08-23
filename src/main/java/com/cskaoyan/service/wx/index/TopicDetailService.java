package com.cskaoyan.service.wx.index;

import com.cskaoyan.bean.admin.goods.GoodsComment;
import com.cskaoyan.bean.admin.spread.MallTopic;
import com.cskaoyan.bean.wx.index.vo.WxCommentDate;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 18:20 2019/8/22
 */
public interface TopicDetailService {
    String selectGoodsByTopicId(int id);

    MallTopic selectTopicById(int id);

    List<GoodsComment> queryCommentsByValueIdAndPicture(Integer valueId, Integer type, Integer showType);

    WxCommentDate<GoodsComment> getCommentsForWx(Integer page, Integer size, Integer valueId, Integer type);

    List<MallTopic> getRelatedTopic(int id);
}
