package com.cskaoyan.mapper.coreservice;

import com.cskaoyan.bean.wx.coreservice.CollectGoods;
import com.cskaoyan.bean.wx.coreservice.Footprint;
import com.cskaoyan.bean.wx.coreservice.FootprintExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FootprintMapper {
    long countByExample(FootprintExample example);

    int deleteByExample(FootprintExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Footprint record);

    int insertSelective(Footprint record);

    List<Footprint> selectByExample(FootprintExample example);

    Footprint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Footprint record, @Param("example") FootprintExample example);

    int updateByExample(@Param("record") Footprint record, @Param("example") FootprintExample example);

    int updateByPrimaryKeySelective(Footprint record);

    int updateByPrimaryKey(Footprint record);

    List<CollectGoods> queryListByUser(int uid);
}