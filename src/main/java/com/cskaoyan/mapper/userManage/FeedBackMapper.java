package com.cskaoyan.mapper.userManage;

import com.cskaoyan.bean.admin.userManage.FeedBack;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedBackMapper {

    // 查询所有意见反馈
    List<FeedBack> queryFeedBack();

    // 通过用户名查询反馈
    List<FeedBack> queryFeedBackByUsername(@Param("username") String username);

    // 通过反馈id查询全部反馈
    List<FeedBack> queryFeedBackByFeedbackId(@Param("id") String id);

    // 通过反馈id和用户名查询反馈
    List<FeedBack> queryFeedBackByFeedBackIdAndUsername(@Param("id") String id, @Param("username") String username);

    //插入一条用户反馈
    void insertFeedBack(@Param("feedBack") FeedBack feedBack);
}
