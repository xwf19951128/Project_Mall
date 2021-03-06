package com.cskaoyan.controller.admin.storage;

import com.cskaoyan.annotation.SystemLog;
import com.cskaoyan.bean.admin.storage.Storage;
import com.cskaoyan.bean.admin.vo.DataBean;
import com.cskaoyan.oss.MyOssClient;
import com.cskaoyan.service.admin.storage.StorageService;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * @Author: XiaoLei
 * @Date Created in 21:00 2019/8/16
 */
@RestController
public class StorageController {

    @Autowired
    StorageService storageService;

    @Autowired
    MyOssClient myOssClient;


    @RequestMapping("admin/storage/list")
    @SystemLog(desc = "查看存储对象")
    public ResponseVo StorageList(int page,int limit,String key,String name,String order,String sort){
        ResponseVo<Object> responseVo = new ResponseVo<>();
        DataBean<Storage> storageDataBean =storageService.selectStorage(page,limit,key,name,order,sort);
        responseVo.setData(storageDataBean);
        responseVo.setErrno(0);
        responseVo.setErrmsg("成功");
        return responseVo;
    }

    //文件上传，并存入数据库
    @RequestMapping("admin/storage/create")
    @SystemLog(desc = "文件上传")
    //抓包去看mulitpart的请求，需要和input标签的name一致
    public ResponseVo uploadFile(MultipartFile file) throws IOException {
        ResponseVo responseVo = new ResponseVo();
        if (file != null) {
            Storage storage = myOssClient.ossFileUpload(file);
            storage.setAddTime(new Date());
            storage.setUpdateTime(new Date());
            //将该图片插入数据库
            int i = storageService.createStorage(storage);
            //从数据库中取出该文件，看能否取出
            Storage storage1 = storageService.selectStorageById(storage.getId());
            if (i == 1 && storage1 != null) {
                responseVo.setData(storage);
                responseVo.setErrno(0);
                responseVo.setErrmsg("成功");
                return responseVo;
            }else{
                responseVo.setErrmsg("失败");
                return responseVo;
            }
        }else{
            responseVo.setErrmsg("图片为空");
            return responseVo;
        }

    }

    @RequestMapping("/admin/storage/update")
    @SystemLog(desc = "更新存储对象")
    public ResponseVo updateStorage(@RequestBody Storage storage){
        ResponseVo responseVo = new ResponseVo();

        int i =storageService.updateStorage(storage);
        Storage storage1 =storageService.selectStorageById(storage.getId());
        if(i!=0){
            responseVo.setErrmsg("成功");
            responseVo.setErrno(0);
            responseVo.setData(storage1);
            return responseVo;
        }
        responseVo.setErrmsg("失败");
        return responseVo;
    }

    @RequestMapping("/admin/storage/delete")
    @SystemLog(desc = "删除存储对象")
    public ResponseVo deleteStorage(@RequestBody Storage storage){
        ResponseVo responseVo = new ResponseVo();
        int i = storageService.deleteStorage(storage);
        if(i!=0){
            responseVo.setErrno(0);
            responseVo.setErrmsg("成功");
            return responseVo;
        }
        responseVo.setErrno(1);
        responseVo.setErrmsg("失败");
        return responseVo;
    }


}
