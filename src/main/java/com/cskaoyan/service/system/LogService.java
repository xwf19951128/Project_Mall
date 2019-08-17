package com.cskaoyan.service.system;

import com.cskaoyan.bean.system.Log;
import com.cskaoyan.bean.vo.DataBean;

/**
 * @Author: XiaoLei
 * @Date Created in 20:22 2019/8/16
 */
public interface LogService {
    DataBean<Log> selectLogs(int page, int limit, String name, String sort, String order);
}
