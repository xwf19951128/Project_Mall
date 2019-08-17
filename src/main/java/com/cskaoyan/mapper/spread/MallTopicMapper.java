package com.cskaoyan.mapper.spread;

import com.cskaoyan.bean.spread.MallTopic;
import com.cskaoyan.bean.spread.MallTopicExample;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallTopicMapper {
    long countByExample(MallTopicExample example);

    int deleteByExample(MallTopicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallTopic record);

    int insertSelective(MallTopic record);

    List<MallTopic> selectByExampleWithBLOBs(MallTopicExample example);

    List<MallTopic> selectByExample(MallTopicExample example);

    MallTopic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallTopic record, @Param("example") MallTopicExample example);

    int updateByExampleWithBLOBs(@Param("record") MallTopic record, @Param("example") MallTopicExample example);

    int updateByExample(@Param("record") MallTopic record, @Param("example") MallTopicExample example);

    int updateByPrimaryKeySelective(MallTopic record);

    int updateByPrimaryKeyWithBLOBs(MallTopic record);

    int updateByPrimaryKey(MallTopic record);

    ArrayList<MallTopic> queryMallTopicList(@Param("title") String title, @Param("subtitle") String subtitle);
}