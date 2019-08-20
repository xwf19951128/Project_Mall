package com.cskaoyan.controller.admin.userManage;

import com.cskaoyan.bean.admin.userManage.DataAndErr;
import com.cskaoyan.bean.admin.userManage.ItemAndTotal;
import com.cskaoyan.bean.admin.userManage.SearchHistory;
import com.cskaoyan.service.admin.userManage.SearchHistoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SearchHistoryController {

    @Autowired
    SearchHistoryService searchHistoryService;

    @RequestMapping("/admin/history/list")
    @ResponseBody
    DataAndErr querySearchHistory(String userId, String keyword, String sort, String order, int page, int limit) {
        DataAndErr dataAndErr = new DataAndErr();
        ItemAndTotal<SearchHistory> itemAndTotal = new ItemAndTotal<>();
        List<SearchHistory> searchHistories;

        PageHelper.startPage(page, limit);

        if ((userId == null || "".equals(userId)) && (keyword == null || "".equals(keyword))) {
            // 查询全部搜索历史
            searchHistories = searchHistoryService.querySearchHistory();
        } else if (userId == null || "".equals(userId)) {
            // 通过关键字查询搜索历史
            searchHistories = searchHistoryService.querySearchHistoryByKeyword("%" + keyword + "%");
        } else if (keyword == null || "".equals(keyword)) {
            // 通过用户id查询全部搜索历史
            searchHistories = searchHistoryService.querySearchHistoryByUserId(userId);
        } else {
            // 通过用户id和关键字查询全部搜索历史
            searchHistories = searchHistoryService.querySearchHistoryByUserIdAndKeyword(userId, "%" + keyword + "%");
        }

        PageInfo<SearchHistory> pageInfo = new PageInfo<>(searchHistories);
        int total = (int) pageInfo.getTotal();

        // 封装item和total
        itemAndTotal.setItems(searchHistories);
        itemAndTotal.setTotal(total);

        // 封装data、errMsg、errno
        dataAndErr.setData(itemAndTotal);
        dataAndErr.setErrmsg("成功");
        dataAndErr.setErrno(0);

        // 返回这个JavaBean
        return dataAndErr;
    }
}
