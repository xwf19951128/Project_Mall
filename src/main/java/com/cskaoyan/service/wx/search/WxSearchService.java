package com.cskaoyan.service.wx.search;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.mall.category.Category;
import com.cskaoyan.bean.admin.mall.keyword.Keyword;
import com.cskaoyan.bean.wx.search.HistoryKeyword;
import com.cskaoyan.bean.wx.search.SearchGoods;
import com.cskaoyan.bean.wx.search.SearchIndex;

import java.util.Date;
import java.util.List;

public interface WxSearchService {
    //查询三种keyword，并存入javaBean中
    public SearchIndex querySearchIndex();

    //查询searchHelper
    public List<HistoryKeyword> querySearchHelper(String keyword);

    //把当前查询关键字存入到数据库的keyword表中
    void insertKeyword(Keyword keyword1);

    //真正的搜索功能
    List<SearchGoods> queryGoodsByKeyword(String keyword, String sort, String order, int categoryId);

    //根据categoryId，查询对应的category对象
    Category queryCategoryById(int id);

    List<Keyword> queryKeyword(String keyword);

    void updateKeyword(Keyword keyword1);

    void deleteAllRecords();

}
