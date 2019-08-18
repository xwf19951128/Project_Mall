package com.cskaoyan.service.userManage;

import com.cskaoyan.bean.userManage.FeedBack;

import java.util.List;

public interface FeedBackService {
    // 查询所有意见反馈
    List<FeedBack> queryFeedBack();

    // 通过用户名查询反馈
    List<FeedBack> queryFeedBackByUsername(String username);

    // 通过反馈id查询全部反馈
    List<FeedBack> queryFeedBackByFeedbackId(String id);

    // 通过反馈id和用户名查询反馈
    List<FeedBack> queryFeedBackByFeedBackIdAndUsername(String id, String username);
}
