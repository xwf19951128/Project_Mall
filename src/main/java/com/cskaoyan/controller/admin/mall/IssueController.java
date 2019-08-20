package com.cskaoyan.controller.admin.mall;

import com.cskaoyan.bean.admin.mall.issue.Issue;
import com.cskaoyan.bean.admin.mall.issue.IssuePage;
import com.cskaoyan.service.admin.mall.IssueService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("admin/issue")
public class IssueController {

    @Autowired
    IssueService issueService;

    @RequestMapping("list")
    public ResponseVo getIssueList(int page, int limit,String question, String order, String sort) {
        StringBuilder stringBuilder = new StringBuilder();
        if (question != null) {
            stringBuilder.append("%").append(question).append("%");
        }
        String orderBy = sort + " " + order;
        PageHelper.startPage(page,limit,orderBy);
        List<Issue> items =  issueService.getIssueList(stringBuilder.toString());
        IssuePage issuePage = new IssuePage();
        PageInfo<Issue> orderPageInfo = new PageInfo<>(items);
        issuePage.setItems(items);
        issuePage.setTotal((int)orderPageInfo.getTotal());
        return ResponseUtil.success(issuePage);
    }


    @RequestMapping("update")
    public ResponseVo updateIssue(@RequestBody Issue issue) {
        issue.setUpdateTime(new Date());
        issueService.updateIssue(issue);
        return ResponseUtil.success(null);
    }

    @RequestMapping("create")
    public ResponseVo createIssue(@RequestBody Issue issue) {
        issue.setAddTime(new Date());
        issue.setUpdateTime(new Date());
        issue.setDeleted(false);
        issueService.insertIssue(issue);
        issue = issueService.getIssueByAnswer(issue);
        return ResponseUtil.success(issue);
    }

    @RequestMapping("delete")
    public ResponseVo deleteIssue(@RequestBody Issue issue) {
        issueService.deleteIssue(issue);
        return ResponseUtil.success(null);
    }

}
