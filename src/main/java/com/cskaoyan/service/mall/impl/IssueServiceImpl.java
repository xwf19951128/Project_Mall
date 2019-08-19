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
        } else {
            items = issueMapper.getIssueListByQuestion(question);
        }
        return items;
    }

    @Override
    public void updateIssue(Issue issue) {
        issueMapper.updateByPrimaryKey(issue);
    }

    @Override
    public void insertIssue(Issue issue) {
        issueMapper.insert(issue);
    }

    @Override
    public Issue getIssueByAnswer(Issue issue) {
        return issueMapper.selectByAnswer(issue.getAnswer());
    }

    @Override
    public void deleteIssue(Issue issue) {
        issueMapper.deleteByPrimaryKey(issue.getId());
    }
}
