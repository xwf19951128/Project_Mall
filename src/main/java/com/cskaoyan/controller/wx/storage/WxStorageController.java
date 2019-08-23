package com.cskaoyan.controller.wx.storage;

import com.cskaoyan.bean.admin.storage.Storage;
import com.cskaoyan.bean.admin.userManage.FeedBack;
import com.cskaoyan.bean.wx.login.WxUser;
import com.cskaoyan.oss.MyOssClient;
import com.cskaoyan.service.admin.storage.StorageService;
import com.cskaoyan.service.admin.userManage.FeedBackService;
import com.cskaoyan.service.wx.login.WxLoginService;
import com.cskaoyan.service.wx.tools.AddressService_wx;
import com.cskaoyan.util.ResponseUtil;
import com.cskaoyan.util.ResponseVo;
import com.cskaoyan.util.wx.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@RestController
public class WxStorageController {
    @Autowired
    MyOssClient ossClient;
    @Autowired
    StorageService storageService;
    @Autowired
    AddressService_wx addressService_wx;
    @Autowired
    WxLoginService wxLoginService;

    @RequestMapping("/wx/storage/upload")
    public ResponseVo upload(MultipartFile file) throws IOException {
        Storage storage = ossClient.ossFileUpload(file);
        storage.setAddTime(new Date());
        storage.setUpdateTime(new Date());
        //将该图片插入数据库
        storageService.createStorage(storage);
        return ResponseUtil.success(storage);
    }

    @RequestMapping("/wx/feedback/submit")
    public ResponseVo submit(@RequestBody HashMap<String,Object> paramMap,HttpServletRequest request){
        String tokenKey = request.getHeader("X-Litemall-Token");
        //通过请求头获得userId，进而可以获得一切关于user的信息
        Integer userId = UserTokenManager.getUserId(tokenKey);
        String username = wxLoginService.queryUsernameById(userId);

        FeedBack feedBack = new FeedBack();
        String content = (String) paramMap.get("content");
        String feedType = (String) paramMap.get("feedType");
        //前端传过来一个boolean型数据，把它转为int类型
        boolean booPicture = (boolean) paramMap.get("hasPicture");
        int hasPicture = 0;
        if (booPicture==true){
            hasPicture=1;
        }
        String mobile = (String) paramMap.get("mobile");
        //前端传过来一个Object集合，手动把它转为String数组
        ArrayList pics = (ArrayList) paramMap.get("picUrls");
        Object[] objects = pics.toArray();
        String[] picUrls = new String[objects.length];
        for (int i = 0; i <objects.length ; i++) {
            picUrls[i] =  objects[i].toString();
        }
        feedBack.setContent((String) content);
        feedBack.setFeedType(feedType);
        feedBack.setHasPicture(hasPicture);
        feedBack.setMobile(mobile);
        feedBack.setPicUrls(picUrls);
        feedBack.setAddTime(new Date());
        feedBack.setUpdateTime(new Date());
        feedBack.setUserId(userId);
        feedBack.setUsername(username);

        addressService_wx.insertFeedBack(feedBack);
        return ResponseUtil.success();
    }
}
