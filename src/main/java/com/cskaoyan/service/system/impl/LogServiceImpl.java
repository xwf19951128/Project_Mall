package com.cskaoyan.service.system.impl;

import com.cskaoyan.bean.system.Log;
import com.cskaoyan.bean.vo.DataBean;
import com.cskaoyan.mapper.system.LogMapper;
import com.cskaoyan.service.system.LogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 20:23 2019/8/16
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    @Override
    public DataBean<Log> selectLogs(int page, int limit, String name, String sort, String order) {
        Page<Object> pageResult = PageHelper.startPage(page, limit);
        List<Log> logs = logMapper.selectLogs(name,sort,order);
        DataBean<Log> dataBean = new DataBean<>();
        dataBean.setTotal((int) pageResult.getTotal());
        dataBean.setItems(logs);
        return dataBean;
    }
}
