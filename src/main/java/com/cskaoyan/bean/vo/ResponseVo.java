package com.cskaoyan.bean.vo;

/**
 * @Author: XiaoLei
 * @Date Created in 22:38 2019/8/15
 */
public class ResponseVo {
    int errno;
    String errmsg;
    String data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ResponseVo() {
    }

    public ResponseVo(int errno, String errmsg, String data) {
        this.errno = errno;
        this.errmsg = errmsg;
        this.data = data;
    }
}
