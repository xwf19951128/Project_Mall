package com.cskaoyan.service.mall;

import com.cskaoyan.bean.mall.keyword.Keyword;

import java.util.List;

public interface KeywordService {
    List<Keyword> getKeywordList(String keywords, String urls);

}
