package com.cskaoyan.controller.mall;

import com.cskaoyan.bean.mall.issue.Issue;
import com.cskaoyan.bean.mall.issue.IssuePage;
import com.cskaoyan.bean.mall.order.Order;
import com.cskaoyan.bean.mall.order.OrderPage;
import com.cskaoyan.service.mall.IssueService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
