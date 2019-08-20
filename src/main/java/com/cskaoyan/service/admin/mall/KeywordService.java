package com.cskaoyan.service.admin.mall;

import com.cskaoyan.bean.admin.mall.keyword.Keyword;

import java.util.List;

public interface KeywordService {
    List<Keyword> getKeywordList(String keyword, String url);

    void deleteKeyword(Keyword keyword);

    Keyword createKeyword(Keyword keyword);

    void updateKeyword(Keyword keyword);
}
