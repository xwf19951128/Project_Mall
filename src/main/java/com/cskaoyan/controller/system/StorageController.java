package com.cskaoyan.controller.system;

import com.cskaoyan.bean.system.Storage;
import com.cskaoyan.bean.vo.DataBean;
import com.cskaoyan.service.system.StorageService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: XiaoLei
 * @Date Created in 21:00 2019/8/16
 */
@RestController
public class StorageController {

    @Autowired
    StorageService storageService;

    @RequestMapping("admin/storage/list")
    public ResponseVo StorageList(int page, int limit, String key, String name, String order, String sort){
        ResponseVo<Object> responseVo = new ResponseVo<>();
        DataBean<Storage> storageDataBean =storageService.selectStorage(page,limit,key,name,order,sort);
        responseVo.setData(storageDataBean);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;
    }
}
