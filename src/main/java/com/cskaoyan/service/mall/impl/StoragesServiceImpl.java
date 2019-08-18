package com.cskaoyan.service.mall.impl;

import com.cskaoyan.bean.mall.file.Storage;
import com.cskaoyan.mapper.mall.StoragesMapper;
import com.cskaoyan.service.mall.StoragesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StoragesServiceImpl implements StoragesService {

    @Autowired
    StoragesMapper storagesMapper;

    @Override
    public void uploadPic(Storage storage) {
        storagesMapper.uploadPic(storage);
    }
}
