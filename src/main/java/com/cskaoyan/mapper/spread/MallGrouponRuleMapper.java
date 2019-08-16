package com.cskaoyan.mapper.spread;

import com.cskaoyan.bean.spread.MallGrouponRule;
import com.cskaoyan.bean.spread.MallGrouponRuleExample;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallGrouponRuleMapper {
    long countByExample(MallGrouponRuleExample example);

    int deleteByExample(MallGrouponRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallGrouponRule record);

    int insertSelective(MallGrouponRule record);

    List<MallGrouponRule> selectByExample(MallGrouponRuleExample example);

    MallGrouponRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallGrouponRule record, @Param("example") MallGrouponRuleExample example);

    int updateByExample(@Param("record") MallGrouponRule record, @Param("example") MallGrouponRuleExample example);

    int updateByPrimaryKeySelective(MallGrouponRule record);

    int updateByPrimaryKey(MallGrouponRule record);

    ArrayList<MallGrouponRule> queryMallGrouponRuleList(@Param("content") String content, @Param("name") String name);
}