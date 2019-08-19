package com.cskaoyan.service.mall;

import com.cskaoyan.bean.mall.issue.Issue;

import java.util.List;

public interface IssueService {
    List<Issue> getIssueList(String question);

    void updateIssue(Issue issue);

    void insertIssue(Issue issue);

    Issue getIssueByAnswer(Issue issue);

    void deleteIssue(Issue issue);
}
