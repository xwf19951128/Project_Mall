package com.cskaoyan.mapper.userManage;

import com.cskaoyan.bean.userManage.SearchHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SearchHistoryMapper {

    // 查询所有搜索历史
    List<SearchHistory> querySearchHistory();

    // 通过关键字查询搜索历史
    List<SearchHistory> querySearchHistoryByKeyword(@Param("keyword") String keyword);

    // 通过用户id查询全部搜索历史
    List<SearchHistory> querySearchHistoryByUserId(@Param("userId") String userId);

    // 通过用户id和关键字查询全部搜索历史
    List<SearchHistory> querySearchHistoryByUserIdAndKeyword(@Param("userId") String userId, @Param("keyword") String keyword);
}
