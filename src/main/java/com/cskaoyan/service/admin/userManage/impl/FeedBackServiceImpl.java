package com.cskaoyan.service.admin.userManage.impl;

import com.cskaoyan.bean.admin.userManage.FeedBack;
import com.cskaoyan.mapper.userManage.FeedBackMapper;
import com.cskaoyan.service.admin.userManage.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    FeedBackMapper feedBackMapper;

    @Override
    public List<FeedBack> queryFeedBack() {
        return feedBackMapper.queryFeedBack();
    }

    @Override
    public List<FeedBack> queryFeedBackByUsername(String username) {
        return feedBackMapper.queryFeedBackByUsername(username);
    }

    @Override
    public List<FeedBack> queryFeedBackByFeedbackId(String id) {
        return feedBackMapper.queryFeedBackByFeedbackId(id);
    }

    @Override
    public List<FeedBack> queryFeedBackByFeedBackIdAndUsername(String id, String username) {
        return feedBackMapper.queryFeedBackByFeedBackIdAndUsername(id, username);
    }
}
