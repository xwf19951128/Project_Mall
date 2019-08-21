package com.cskaoyan.service.wx.search;

import com.cskaoyan.bean.wx.search.HistoryKeyword;
import com.cskaoyan.bean.wx.search.SearchIndex;
import com.cskaoyan.mapper.mall.KeywordMapper;
import com.cskaoyan.service.wx.login.WxLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WxSearchServiceImpl implements WxSearchService {
    @Autowired
    KeywordMapper keywordMapper;


    @Override
    public SearchIndex querySearchIndex() {
        return keywordMapper.querySearchIndex();
    }

    @Override
    public List<HistoryKeyword> querySearchHelper(String keyword) {
        //我想让搜索功能更加完善，就需要拆分keyword字符串为一个个的字符
        //然后依次拼接这些字符为一个模糊查询的样式！
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <keyword.length() ; i++) {
            char c = keyword.charAt(i);
            sb.append("%"+c+"%");
        }
        String s = sb.toString();
        return keywordMapper.querySearchHelper(s);
    }


}
