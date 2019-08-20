package com.cskaoyan.service.wx.search;

import com.cskaoyan.bean.wx.search.SearchIndex;
import com.cskaoyan.mapper.mall.KeywordMapper;
import com.cskaoyan.service.wx.login.WxLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WxSearchServiceImpl implements WxSearchService {
    @Autowired
    KeywordMapper keywordMapper;


    @Override
    public SearchIndex querySearchIndex() {
        return keywordMapper.querySearchIndex();
    }



}
