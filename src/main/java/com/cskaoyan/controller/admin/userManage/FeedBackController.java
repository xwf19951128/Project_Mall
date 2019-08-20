package com.cskaoyan.controller.admin.userManage;

import com.cskaoyan.bean.admin.userManage.*;
import com.cskaoyan.service.admin.userManage.FeedBackService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FeedBackController {

    @Autowired
    FeedBackService feedBackService;

    @RequestMapping("/admin/feedback/list")
    @ResponseBody
    DataAndErr queryFeedBack(String id, String username, String sort, String order, int page, int limit) {
        DataAndErr dataAndErr = new DataAndErr();
        ItemAndTotal<FeedBack> itemAndTotal = new ItemAndTotal<>();
        List<FeedBack> feedBacks;
        PageHelper.startPage(page, limit);

        if ((id == null || "".equals(id)) && (username == null || "".equals(username))) {
            // 查询全部反馈
            feedBacks = feedBackService.queryFeedBack();
        } else if (id == null || "".equals(id)) {
            // 通过用户名查询反馈
            feedBacks = feedBackService.queryFeedBackByUsername("%" + username + "%");
        } else if (username == null || "".equals(username)) {
            // 通过反馈id查询全部反馈
            feedBacks = feedBackService.queryFeedBackByFeedbackId(id);
        } else {
            // 通过反馈id和用户名查询反馈
            feedBacks = feedBackService.queryFeedBackByFeedBackIdAndUsername(id, "%" + username + "%");
        }

        PageInfo<FeedBack> pageInfo = new PageInfo<>(feedBacks);
        int total = (int) pageInfo.getTotal();

        // 封装item和total
        itemAndTotal.setItems(feedBacks);
        itemAndTotal.setTotal(total);

        // 封装data、errMsg、errno
        dataAndErr.setData(itemAndTotal);
        dataAndErr.setErrmsg("成功");
        dataAndErr.setErrno(0);

        // 返回这个JavaBean
        return dataAndErr;
    }












    DataAndErr queryFeedBack() {
        DataAndErr dataAndErr = new DataAndErr();
        ItemAndTotal<FeedBack> feedBackItemAndTotal = new ItemAndTotal<>();
        // 调用service查询意见反馈
        List<FeedBack> feedBacks = feedBackService.queryFeedBack();
        // 判断有没有查询出数据
        if (feedBacks != null) {
            // 封装itemAndTotal
            feedBackItemAndTotal.setItems(feedBacks);
            feedBackItemAndTotal.setTotal(feedBacks.size());
            // 封装dataAndErr
            dataAndErr.setData(feedBackItemAndTotal);
            dataAndErr.setErrmsg("成功");
            dataAndErr.setErrno(0);
        } else {
            dataAndErr.setErrmsg("失败");
            dataAndErr.setErrno(-1);
        }
        // 返回dataAndErr
        return dataAndErr;
    }
}
