package com.cskaoyan.service.admin.system;

import com.cskaoyan.bean.admin.system.Log;
import com.cskaoyan.bean.admin.vo.DataBean;

/**
 * @Author: XiaoLei
 * @Date Created in 20:22 2019/8/16
 */
public interface LogService {
    DataBean<Log> selectLogs(int page, int limit, String name, String sort, String order);

    int insertLog(Log log);
}
