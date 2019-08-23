package com.cskaoyan.controller.wx.index;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.goods.GoodsComment;
import com.cskaoyan.bean.admin.goods.GoodsCommentExample;
import com.cskaoyan.bean.admin.goods.PageParams4Goods;
import com.cskaoyan.bean.admin.spread.MallTopic;
import com.cskaoyan.bean.admin.userManage.User;
import com.cskaoyan.bean.wx.index.vo.UserInfo;
import com.cskaoyan.bean.wx.index.vo.WxCommentDate;
import com.cskaoyan.bean.wx.login.WxUser;
import com.cskaoyan.mapper.goods.GoodsCommentMapper;
import com.cskaoyan.mapper.login.WxUserMapper;
import com.cskaoyan.service.admin.goods.GoodsCommentService;
import com.cskaoyan.service.wx.index.TopicDetailService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @Autowired
    GoodsCommentMapper goodsCommentMapper;

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


    @Autowired
    WxUserMapper wxUserMapper;

    /*显示商品评价列表*/
    /*type表示评价类型，showType表示是否有图*/
    @RequestMapping("/wx/comment/list")
    public ResponseVo listGoodsComment(Integer valueId, Integer type, Integer size, Integer page, Integer showType){
        //分页和查询
        ResponseVo responseVo = new ResponseVo();

        PageHelper pageHelper = new PageHelper();
        Page<Object> resultPage = PageHelper.startPage(page, size);
        //查询有图的评论
        HashMap<Object, Object> hashMap = new HashMap<>();
//        if(showType==1){


            GoodsCommentExample goodsCommentExample = new GoodsCommentExample();
            //如果有图片，找出图片的评论
            if(showType==1){
                goodsCommentExample.createCriteria().andValueIdEqualTo(valueId).andTypeEqualTo((byte) 0).andHasPictureEqualTo(true);
            }
             goodsCommentExample.createCriteria().andValueIdEqualTo(valueId).andTypeEqualTo((byte) 0);

            List<GoodsComment> goodsComments = goodsCommentMapper.selectByExample(goodsCommentExample);

            ArrayList<Map<String, Object>> mapArrayList = new ArrayList<>(goodsComments.size());
            for (GoodsComment goodsComment : goodsComments) {
                Map<String, Object> commentVo = new HashMap<>();
                commentVo.put("addTime", goodsComment.getAddTime());
                commentVo.put("content", goodsComment.getContent());
                commentVo.put("picList", goodsComment.getPicUrls());

                WxUser wxUser = wxUserMapper.selectByPrimaryKey(goodsComment.getUserId());
                UserInfo userInfo = new UserInfo();
                userInfo.setAvatarurl(wxUser.getAvatar());
                userInfo.setNickName(wxUser.getNickname());
                commentVo.put("userInfo", userInfo);
                mapArrayList.add(commentVo);
            }

            hashMap.put("data",mapArrayList);
            hashMap.put("count",resultPage.size());
            hashMap.put("currentPage",page);

            responseVo.setData(hashMap);
            responseVo.setErrmsg("成功");
            responseVo.setErrno(0);
            return responseVo;
//        }
        //查询其他所有的评论

//        GoodsCommentExample goodsCommentExample2 = new GoodsCommentExample();
//        goodsCommentExample2.createCriteria().andValueIdEqualTo(valueId);
//        List<GoodsComment> goodsComments = goodsCommentMapper.selectByExample(goodsCommentExample2);
//
//        hashMap.put("data",goodsComments);
//        hashMap.put("count",resultPage.size());
//        hashMap.put("currentPage",page);
//
//        responseVo.setData(hashMap);
//        responseVo.setErrmsg("成功");
//        responseVo.setErrno(0);
//        return responseVo;
//
    }

    @RequestMapping("/wx/comment/count")
    public ResponseVo getCommentCount(@Param("valueId") int valueId,@Param("type") int type){
        ResponseVo responseVo = new ResponseVo();
        GoodsCommentExample goodsCommentExample = new GoodsCommentExample();
        //先找有图的数量
        goodsCommentExample.createCriteria().andHasPictureEqualTo(true).andValueIdEqualTo(valueId);
        long count = goodsCommentMapper.countByExample(goodsCommentExample);
        //在找所有的评论
        GoodsCommentExample goodsCommentExample2=new GoodsCommentExample();
        goodsCommentExample2.createCriteria().andValueIdEqualTo(valueId);
        long count1 = goodsCommentMapper.countByExample(goodsCommentExample2);
        HashMap hashMap = new HashMap();
        hashMap.put("allCount",count1);
        hashMap.put("hasPicCount",count);
        responseVo.setData(hashMap);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
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
