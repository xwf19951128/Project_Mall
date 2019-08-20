package com.cskaoyan.service.admin.userManage.impl;

import com.cskaoyan.bean.admin.userManage.SearchHistory;
import com.cskaoyan.mapper.userManage.SearchHistoryMapper;
import com.cskaoyan.service.admin.userManage.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchHistoryServiceImpl implements SearchHistoryService {

    @Autowired
    SearchHistoryMapper searchHistoryMapper;

    // 查询全部搜索历史
    @Override
    public List<SearchHistory> querySearchHistory() {
        return searchHistoryMapper.querySearchHistory();
    }

    @Override
    public List<SearchHistory> querySearchHistoryByKeyword(String keyword) {
        return searchHistoryMapper.querySearchHistoryByKeyword(keyword);
    }

    @Override
    public List<SearchHistory> querySearchHistoryByUserId(String userId) {
        return searchHistoryMapper.querySearchHistoryByUserId(userId);
    }

    @Override
    public List<SearchHistory> querySearchHistoryByUserIdAndKeyword(String userId, String keyword) {
        return searchHistoryMapper.querySearchHistoryByUserIdAndKeyword(userId, keyword);
    }
}
