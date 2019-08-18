package com.cskaoyan.service.userManage;

import com.cskaoyan.bean.userManage.SearchHistory;

import java.util.List;

public interface SearchHistoryService {

    // 查询所有搜索历史
    List<SearchHistory> querySearchHistory();

    // 通过关键字查询搜索历史
    List<SearchHistory> querySearchHistoryByKeyword(String keyword);

    // 通过用户id查询全部搜索历史
    List<SearchHistory> querySearchHistoryByUserId(String userId);

    // 通过用户id和关键字查询全部搜索历史
    List<SearchHistory> querySearchHistoryByUserIdAndKeyword(String userId, String keyword);
}
