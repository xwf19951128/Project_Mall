package com.cskaoyan.service.mall.impl;

import com.cskaoyan.bean.mall.file.Storage;
import com.cskaoyan.mapper.mall.StorageMapper;
import com.cskaoyan.service.mall.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageMapper storageMapper;

    @Override
    public void uploadPic(Storage storage) {
        storageMapper.uploadPic(storage);
    }
}
