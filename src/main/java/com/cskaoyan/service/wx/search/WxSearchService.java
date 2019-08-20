package com.cskaoyan.service.wx.search;

import com.cskaoyan.bean.wx.search.SearchIndex;

public interface WxSearchService {
    //查询三种keyword，并存入javaBean中
    public SearchIndex querySearchIndex();
}
