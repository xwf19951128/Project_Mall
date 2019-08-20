package com.cskaoyan.bean.wx.search;

import com.cskaoyan.bean.admin.mall.keyword.Keyword;

import java.util.List;

public class SearchIndex {
    Keyword defaultKeyword;
    List<Keyword> hotKeywordList;
    List<HistoryKeyword> historyKeywordList;

    public Keyword getDefaultKeyword() {
        return defaultKeyword;
    }

    public void setDefaultKeyword(Keyword defaultKeyword) {
        this.defaultKeyword = defaultKeyword;
    }

    public List<Keyword> getHotKeywordList() {
        return hotKeywordList;
    }

    public void setHotKeywordList(List<Keyword> hotKeywordList) {
        this.hotKeywordList = hotKeywordList;
    }

    public List<HistoryKeyword> getHistoryKeywordList() {
        return historyKeywordList;
    }

    public void setHistoryKeywordList(List<HistoryKeyword> historyKeywordList) {
        this.historyKeywordList = historyKeywordList;
    }
}
