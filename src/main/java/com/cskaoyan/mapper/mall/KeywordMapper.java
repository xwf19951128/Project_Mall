package com.cskaoyan.mapper.mall;

import com.cskaoyan.bean.admin.goods.Goods;
import com.cskaoyan.bean.admin.mall.keyword.Keyword;
import com.cskaoyan.bean.admin.mall.keyword.KeywordExample;
import com.cskaoyan.bean.wx.search.HistoryKeyword;
import com.cskaoyan.bean.wx.search.SearchGoods;
import com.cskaoyan.bean.wx.search.SearchIndex;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KeywordMapper {
    long countByExample(KeywordExample example);

    int deleteByExample(KeywordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Keyword record);

    int insertSelective(Keyword record);

    List<Keyword> selectByExample(KeywordExample example);

    Keyword selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Keyword record, @Param("example") KeywordExample example);

    int updateByExample(@Param("record") Keyword record, @Param("example") KeywordExample example);

    int updateByPrimaryKeySelective(Keyword record);

    int updateByPrimaryKey(Keyword record);

    List<Keyword> getKeywordList(@Param("keyword") String keyword, @Param("url") String url);

    Keyword selectByKeyword(@Param("keyword") String keyword);

    //查询微信搜索需要的index三种索引
    SearchIndex querySearchIndex();
    //查询searchHelper，查询时可以自动提示相关商品字段
    List<HistoryKeyword> querySearchHelper(@Param("keyword")String keyword);

    //查询根据关键字查询相对应的商品
    List<SearchGoods> queryGoodsByKeyword(@Param("chars")char[] chars,@Param("sort")String sort , @Param("order")String order,@Param("categoryId")int categoryId);
}