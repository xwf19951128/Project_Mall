package com.cskaoyan.mapper.mall;

import com.cskaoyan.bean.mall.file.Storage;
import org.apache.ibatis.annotations.Param;

public interface StorageMapper {
    void uploadPic(@Param("storage") Storage storage);
}
