package com.cskaoyan.service.wx.search;

import com.cskaoyan.bean.wx.search.HistoryKeyword;
import com.cskaoyan.bean.wx.search.SearchIndex;

import java.util.List;

public interface WxSearchService {
    //查询三种keyword，并存入javaBean中
    public SearchIndex querySearchIndex();

    //查询searchHelper
    public List<HistoryKeyword> querySearchHelper(String keyword);
}
