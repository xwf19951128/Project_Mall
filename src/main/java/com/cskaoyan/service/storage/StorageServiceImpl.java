package com.cskaoyan.service.storage;

import com.cskaoyan.bean.storage.Storage;
import com.cskaoyan.bean.vo.DataBean;
import com.cskaoyan.mapper.storage.StorageMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 21:07 2019/8/16
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageMapper storageMapper;

    @Override
    public DataBean<Storage> selectStorage(int page, int limit, String key, String name, String order, String sort) {
        Page<Storage> pageResult = PageHelper.startPage(page, limit);
        DataBean<Storage> storageDataBean = new DataBean<>();
        List<Storage> storages = storageMapper.selectStorage(key,name,order,sort);
        storageDataBean.setItems(storages);
        storageDataBean.setTotal((int) pageResult.getTotal());
        return storageDataBean;
    }

    @Override
    public int createStorage(Storage storage) {
        return storageMapper.createStorage(storage);
    }

    @Override
    public Storage selectStorageById(Integer id) {
        return storageMapper.selectStorageById(id);
    }

    @Override
    public int updateStorage(Storage storage) {
        return storageMapper.updateStorage(storage);
    }

    @Override
    public int deleteStorage(Storage storage) {
        return storageMapper.deleteByPrimaryKey(storage.getId());
    }
}
