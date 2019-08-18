package com.cskaoyan.controller;

import com.cskaoyan.bean.mall.file.Storage;
import com.cskaoyan.service.mall.StoragesService;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("admin")
public class StoragesController {

    @Autowired
    StoragesService storagesService;

    /*图片上传*/
    @RequestMapping("storage/create")
    public ResponseVo uploadPic(HttpServletRequest request, MultipartFile file) throws IOException {
        File pic = new File("c://spring", file.getOriginalFilename());
        file.transferTo(pic);
        Storage upload = new Storage();
        upload.setUrl("123.fpg");
        upload.setType(file.getContentType());
        upload.setAddTime(new Date());
        upload.setUpdateTime(new Date());
        upload.setName(file.getOriginalFilename());
        upload.setSize((int) file.getSize());
        upload.setDeleted(0);
        upload.setKey(UUID.randomUUID().toString()+".jpg");
        storagesService.uploadPic(upload);
        return ResponseUtil.success(upload);
    }

}
