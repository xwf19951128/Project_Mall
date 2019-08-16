package com.cskaoyan.service.system;

import com.cskaoyan.bean.system.Storage;
import com.cskaoyan.bean.vo.DataBean;

/**
 * @Author: XiaoLei
 * @Date Created in 21:07 2019/8/16
 */
public interface StorageService {
    DataBean<Storage> selectStorage(int page, int limit, String key, String name, String order, String sort);
}
