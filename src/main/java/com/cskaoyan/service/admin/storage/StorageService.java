package com.cskaoyan.service.admin.storage;

import com.cskaoyan.bean.admin.storage.Storage;
import com.cskaoyan.bean.admin.vo.DataBean;

/**
 * @Author: XiaoLei
 * @Date Created in 21:07 2019/8/16
 */
public interface StorageService {
    DataBean<Storage> selectStorage(int page, int limit, String key, String name, String order, String sort);

    int createStorage(Storage storage);

    Storage selectStorageById(Integer id);

    int updateStorage(Storage storage);

    int deleteStorage(Storage storage);
}
