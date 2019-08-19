package com.cskaoyan.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.cskaoyan.bean.storage.Storage;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author: XiaoLei
 * @Date Created in 20:30 2019/8/18
 */
@ConfigurationProperties(prefix = "myoss")
@Component
public class MyOssClient {
    String bucket;
    //文件服务器的一个地址
    String endpoint;
    String accesskey;
    String secret;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Storage ossFileUpload(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        String contentType = file.getContentType();
        long size = file.getSize();

        //需要把这个文件上传到服务器中
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);
        objectMetadata.setContentType(contentType);

        String uuid = UUID.randomUUID().toString().replace("-", "");
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, uuid, inputStream, objectMetadata);

        OSSClient ossClient = new OSSClient(endpoint, accesskey, secret);
        ossClient.putObject(putObjectRequest);

        //封装数据进storage
        Storage storage = new Storage();
        storage.setName(file.getOriginalFilename());
        storage.setSize((int) size);
        storage.setType(contentType);
        storage.setUrl("http://" + bucket + "." + endpoint + "/" + uuid);
        storage.setKey(uuid);
        return storage;
    }
}

