package com.cskaoyan.mapper.coreservice;

import com.cskaoyan.bean.wx.coreservice.Collect;
import com.cskaoyan.bean.wx.coreservice.CollectGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    List<CollectGoods> queryListByUser(@Param("id") int uid, @Param("type") short type);
}