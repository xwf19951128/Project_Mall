package com.cskaoyan.mapper.spread;

import com.cskaoyan.bean.admin.spread.GrouponInfo;
import com.cskaoyan.bean.admin.spread.MallGroupon;
import com.cskaoyan.bean.admin.spread.MallGrouponExample;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MallGrouponMapper {
    long countByExample(MallGrouponExample example);

    int deleteByExample(MallGrouponExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallGroupon record);

    int insertSelective(MallGroupon record);

    List<MallGroupon> selectByExample(MallGrouponExample example);

    MallGroupon selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallGroupon record, @Param("example") MallGrouponExample example);

    int updateByExample(@Param("record") MallGroupon record, @Param("example") MallGrouponExample example);

    int updateByPrimaryKeySelective(MallGroupon record);

    int updateByPrimaryKey(MallGroupon record);

    ArrayList<GrouponInfo> queryGrouponInfoList(@Param("content") String content, @Param("name") String name);


}