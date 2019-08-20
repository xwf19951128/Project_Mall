package com.cskaoyan.mapper.mall;

import com.cskaoyan.bean.admin.mall.keyword.Keyword;
import com.cskaoyan.bean.admin.mall.keyword.KeywordExample;
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
}