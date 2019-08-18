package com.cskaoyan.controller.userManage;

import com.cskaoyan.bean.userManage.DataAndErr;
import com.cskaoyan.bean.userManage.FootMark;
import com.cskaoyan.bean.userManage.ItemAndTotal;
import com.cskaoyan.bean.userManage.SearchHistory;
import com.cskaoyan.service.userManage.SearchHistoryService;
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

        if ((userId == null || "".equals(userId)) && (keyword == null || "".equals(keyword))) {
            // 查询全部搜索历史
            searchHistories = searchHistoryService.querySearchHistory();
            // 封装item和total
            itemAndTotal.setItems(searchHistories);
            itemAndTotal.setTotal(searchHistories.size());
        } else if (userId == null || "".equals(userId)) {
            // 通过关键字查询搜索历史
            searchHistories = searchHistoryService.querySearchHistoryByKeyword("%" + keyword + "%");
            // 封装item和total
            itemAndTotal.setItems(searchHistories);
            itemAndTotal.setTotal(searchHistories.size());
        } else if (keyword == null || "".equals(keyword)) {
            // 通过用户id查询全部搜索历史
            searchHistories = searchHistoryService.querySearchHistoryByUserId(userId);
            // 封装item和total
            itemAndTotal.setItems(searchHistories);
            itemAndTotal.setTotal(searchHistories.size());
        } else {
            // 通过用户id和关键字查询全部搜索历史
            searchHistories = searchHistoryService.querySearchHistoryByUserIdAndKeyword(userId, "%" + keyword + "%");
            // 封装item和total
            itemAndTotal.setItems(searchHistories);
            itemAndTotal.setTotal(searchHistories.size());
        }

        // 封装data、errMsg、errno
        dataAndErr.setData(itemAndTotal);
        dataAndErr.setErrmsg("成功");
        dataAndErr.setErrno(0);

        // 返回这个JavaBean
        return dataAndErr;
    }
}
