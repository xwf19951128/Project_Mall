package com.cskaoyan.service.wx.search;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.mall.category.Category;
import com.cskaoyan.bean.admin.mall.keyword.Keyword;
import com.cskaoyan.bean.admin.mall.keyword.KeywordExample;
import com.cskaoyan.bean.wx.search.HistoryKeyword;
import com.cskaoyan.bean.wx.search.SearchGoods;
import com.cskaoyan.bean.wx.search.SearchIndex;
import com.cskaoyan.mapper.goods.GoodsMapper;
import com.cskaoyan.mapper.mall.CategoryMapper;
import com.cskaoyan.mapper.mall.KeywordMapper;
import com.cskaoyan.service.wx.login.WxLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class WxSearchServiceImpl implements WxSearchService {
    @Autowired
    KeywordMapper keywordMapper;
    @Autowired
    CategoryMapper categoryMapper;


    @Override
    public SearchIndex querySearchIndex() {
        return keywordMapper.querySearchIndex();
    }

    @Override
    public List<HistoryKeyword> querySearchHelper(String keyword) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <keyword.length() ; i++) {
            char c = keyword.charAt(i);
            sb.append("%"+c+"%");
        }
        String s = sb.toString();
        return keywordMapper.querySearchHelper(s);
    }

    @Override
    public void insertKeyword(Keyword keyword1) {
        keywordMapper.insertSelective(keyword1);
    }

    @Override
    public List<SearchGoods> queryGoodsByKeyword(String keyword,String sort,String order,int categoryId) {
        //需要把String转为一个String数组
        String s = keyword.replace(" ", "");
        char[] chars = s.toCharArray();
        return keywordMapper.queryGoodsByKeyword(chars,sort,order,categoryId);
    }

    @Override
    public Category queryCategoryById(int id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        return category;
    }

    @Override
    public List<Keyword> queryKeyword(String keyword) {
        KeywordExample keywordExample = new KeywordExample();
        KeywordExample.Criteria criteria = keywordExample.createCriteria();
        criteria.andKeywordEqualTo(keyword);
        return keywordMapper.selectByExample(keywordExample);
    }

    @Override
    public void updateKeyword(Keyword keyword1) {
        keywordMapper.updateByPrimaryKey(keyword1);
    }

    @Override
    public void deleteAllRecords() {
        //删除所有的搜索记录
        KeywordExample keywordExample = new KeywordExample();
        int i = keywordMapper.deleteByExample(keywordExample);
    }


}
