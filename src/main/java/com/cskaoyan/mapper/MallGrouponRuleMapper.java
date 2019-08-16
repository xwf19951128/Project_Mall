package com.cskaoyan.mapper;

import com.cskaoyan.bean.MallGrouponRule;
import com.cskaoyan.bean.MallGrouponRuleExample;
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
}