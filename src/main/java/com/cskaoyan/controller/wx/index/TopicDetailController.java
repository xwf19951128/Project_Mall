package com.cskaoyan.controller.wx.index;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.goods.GoodsComment;
import com.cskaoyan.bean.admin.goods.PageParams4Goods;
import com.cskaoyan.bean.admin.spread.MallTopic;
import com.cskaoyan.bean.wx.index.vo.WxCommentDate;
import com.cskaoyan.mapper.goods.GoodsCommentMapper;
import com.cskaoyan.service.admin.goods.GoodsCommentService;
import com.cskaoyan.service.wx.index.TopicDetailService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author: XiaoLei
 * @Date Created in 17:33 2019/8/22
 */
@RestController
public class TopicDetailController {

    @Autowired
    TopicDetailService topicDetailService;

    @RequestMapping("/wx/topic/detail")
    public ResponseVo TopicDetail(@Param("id") int id){
        ResponseVo responseVo = new ResponseVo();
        HashMap<Object, Object> hashMap = new HashMap<>();
        String goods =topicDetailService.selectGoodsByTopicId(id);
        hashMap.put("goods",goods);
        MallTopic topic =topicDetailService.selectTopicById(id);
        hashMap.put("topic",topic);

        responseVo.setData(hashMap);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;
    }



    /*显示商品评价列表*/
    /*type表示评价类型，showType表示是否有图,姑且先这么想，对应comment表的picture*/
    @RequestMapping("/wx/comment/list")
    public ResponseVo listGoodsComment(Integer valueId, Integer type, Integer size, Integer page, Integer showType){
        //分页和查询
        ResponseVo responseVo = new ResponseVo();

        PageHelper pageHelper = new PageHelper();
        Page<Object> resultPage = PageHelper.startPage(page, size);
        List<GoodsComment> goodsComments =null;
        //查询有图的评论
        if(showType==1){
            goodsComments = topicDetailService.queryCommentsByValueIdAndPicture(valueId, type, showType);
        }
        else{
            //查询其他所有的评论
          WxCommentDate<GoodsComment> wxCommentDate = topicDetailService.getCommentsForWx(page, size, valueId, type);
            goodsComments=wxCommentDate.getData();
        }
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("count",goodsComments.size());
        hashMap.put("currentPage",page);
        hashMap.put("data",goodsComments);

        responseVo.setData(hashMap);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }

    @RequestMapping("/wx/topic/related")
    public ResponseVo getRelatedTopic(@Param("id")int id){
        ResponseVo responseVo = new ResponseVo();
        List<MallTopic> mallTopics = topicDetailService.getRelatedTopic(id);
        responseVo.setData(mallTopics);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;
    }
}
