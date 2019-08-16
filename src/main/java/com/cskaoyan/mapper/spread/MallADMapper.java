package com.cskaoyan.mapper.spread;

import com.cskaoyan.bean.spread.MallAD;
import com.cskaoyan.bean.spread.MallADExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallADMapper {
    long countByExample(MallADExample example);

    int deleteByExample(MallADExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallAD record);

    int insertSelective(MallAD record);

    List<MallAD> selectByExample(MallADExample example);

    MallAD selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallAD record, @Param("example") MallADExample example);

    int updateByExample(@Param("record") MallAD record, @Param("example") MallADExample example);

    int updateByPrimaryKeySelective(MallAD record);

    int updateByPrimaryKey(MallAD record);
}