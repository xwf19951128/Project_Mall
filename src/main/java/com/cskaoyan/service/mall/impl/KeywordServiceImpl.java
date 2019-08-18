package com.cskaoyan.service.mall.impl;

import com.cskaoyan.bean.mall.keyword.Keyword;
import com.cskaoyan.mapper.mall.KeywordMapper;
import com.cskaoyan.service.mall.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    KeywordMapper keywordMapper;

    @Override
    public List<Keyword> getKeywordList(String keywords, String urls) {
        List<Keyword> items = null;
        if (keywords.equals("") && urls.equals("")) {
            items = keywordMapper.getKeywordList();
        }
        return items;
    }
}
