package com.cskaoyan.mapper.goods;

import com.cskaoyan.bean.admin.goods.GoodsComment;
import com.cskaoyan.bean.admin.goods.GoodsCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsCommentMapper {
    long countByExample(GoodsCommentExample example);

    int deleteByExample(GoodsCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsComment record);

    int insertSelective(GoodsComment record);

    List<GoodsComment> selectByExample(GoodsCommentExample example);

    GoodsComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsComment record, @Param("example") GoodsCommentExample example);

    int updateByExample(@Param("record") GoodsComment record, @Param("example") GoodsCommentExample example);

    int updateByPrimaryKeySelective(GoodsComment record);

    int updateByPrimaryKey(GoodsComment record);

    List<GoodsComment> queryCommentsByValueIdAndPicture(@Param("valueId")int valueId,@Param("type")int type,@Param("hasPicture") Integer hasPicture);

    List<GoodsComment> getGoodsCommentByValueIdAndType(Integer valueId, Integer type);
}