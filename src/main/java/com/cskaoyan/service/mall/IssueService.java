package com.cskaoyan.service.mall;

import com.cskaoyan.bean.mall.issue.Issue;

import java.util.List;

public interface IssueService {
    List<Issue> getIssueList(String question);
}
