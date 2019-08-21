package com.cskaoyan.mapper.statistic;

import com.cskaoyan.bean.admin.statistic.LiteMall;
import com.cskaoyan.bean.admin.statistic.LiteMallExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ConfigMapper {
    long countByExample(LiteMallExample example);

    int deleteByExample(LiteMallExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LiteMall record);

    int insertSelective(LiteMall record);

    List<LiteMall> selectByExample(LiteMallExample example);

    LiteMall selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LiteMall record, @Param("example") LiteMallExample example);

    int updateByExample(@Param("record") LiteMall record, @Param("example") LiteMallExample example);

    int updateByPrimaryKeySelective(LiteMall record);

    int updateByPrimaryKey(LiteMall record);
}