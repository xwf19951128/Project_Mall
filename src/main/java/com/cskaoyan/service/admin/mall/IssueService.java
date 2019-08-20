package com.cskaoyan.service.admin.mall;

import com.cskaoyan.bean.admin.mall.issue.Issue;

import java.util.List;

public interface IssueService {
    List<Issue> getIssueList(String question);

    void updateIssue(Issue issue);

    void insertIssue(Issue issue);

    Issue getIssueByAnswer(Issue issue);

    void deleteIssue(Issue issue);
}
