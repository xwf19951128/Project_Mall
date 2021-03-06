package com.cskaoyan.mapper.storage;

import com.cskaoyan.bean.admin.storage.Storage;
import com.cskaoyan.bean.admin.storage.StorageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StorageMapper {
    long countByExample(StorageExample example);

    int deleteByExample(StorageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Storage record);

    int insertSelective(Storage record);

    List<Storage> selectByExample(StorageExample example);

    Storage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Storage record, @Param("example") StorageExample example);

    int updateByExample(@Param("record") Storage record, @Param("example") StorageExample example);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKey(Storage record);

    List<Storage> selectStorage(@Param("key") String key, @Param("name") String name, @Param("order") String order, @Param("sort") String sort);

    int createStorage(Storage storage);

    Storage selectStorageById(@Param("id") Integer id);

    int updateStorage(Storage storage);
}