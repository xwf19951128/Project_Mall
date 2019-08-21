package com.cskaoyan.service.admin.mall.impl;

import com.cskaoyan.bean.admin.mall.keyword.Keyword;
import com.cskaoyan.mapper.mall.KeywordMapper;
import com.cskaoyan.service.admin.mall.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    KeywordMapper keywordMapper;

    @Override
    public List<Keyword> getKeywordList(String keyword, String url) {
        return keywordMapper.getKeywordList(keyword, url);
    }

    @Override
    public void deleteKeyword(Keyword keyword) {
        keywordMapper.deleteByPrimaryKey(keyword.getId());
    }

    @Override
    public Keyword createKeyword(Keyword keyword) {
        keywordMapper.insert(keyword);
        return keywordMapper.selectByKeyword(keyword.getKeyword());
    }

    @Override
    public void updateKeyword(Keyword keyword) {
        keywordMapper.updateByPrimaryKey(keyword);
    }
}
