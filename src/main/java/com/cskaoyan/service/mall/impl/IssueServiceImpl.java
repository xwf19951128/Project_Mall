package com.cskaoyan.service.mall.impl;

import com.cskaoyan.bean.mall.issue.Issue;
import com.cskaoyan.mapper.mall.IssueMapper;
import com.cskaoyan.service.mall.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    IssueMapper issueMapper;

    @Override
    public List<Issue> getIssueList(String question) {
        List<Issue> items = null;
        if (question.equals("")) {
            items = issueMapper.getIssueList();
        }
        return items;
    }
}
