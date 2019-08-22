package com.cskaoyan.mapper.spread;

import com.cskaoyan.bean.admin.spread.GrouponInfo;
import com.cskaoyan.bean.admin.spread.MallGrouponRule;
import com.cskaoyan.bean.admin.spread.MallGrouponRuleExample;

import java.util.ArrayList;
import java.util.List;

import com.cskaoyan.bean.wx.index.GroupOnList;
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
    ArrayList<MallGrouponRule> queryMallGrouponRuleList(@Param("goodsId") String goodsId);
    ArrayList<GrouponInfo> queryGrouponInfoList(@Param("id")String id);

    //获取团购的商品
    List<GroupOnList> getFloorList();
}