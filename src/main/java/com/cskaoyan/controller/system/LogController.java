package com.cskaoyan.controller.system;

import com.cskaoyan.bean.system.Log;
import com.cskaoyan.bean.vo.DataBean;
import com.cskaoyan.service.system.LogService;
import com.cskaoyan.utils.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: XiaoLei
 * @Date Created in 20:18 2019/8/16
 */
@RestController
public class LogController {

    @Autowired
    LogService logService;

    @RequestMapping("admin/log/list")
    public ResponseVo LogList(int page,int limit,String name,String sort,String order){
        ResponseVo<Object> responseVo = new ResponseVo<>();
        DataBean<Log> LogBean = logService.selectLogs(page,limit,name,sort,order);
        responseVo.setData(LogBean);
        responseVo.setErrmsg("成功");
        responseVo.setErrno(0);
        return responseVo;
    }
}
