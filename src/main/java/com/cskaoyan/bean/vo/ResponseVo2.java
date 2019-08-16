package com.cskaoyan.bean.vo;

/**
 * @Author: XiaoLei
 * @Date Created in 23:35 2019/8/15
 */
public class ResponseVo2 {
    int errno;
    String errmsg;
    Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public ResponseVo2(int errno, String errmsg, Data data) {
        this.errno = errno;
        this.errmsg = errmsg;
        this.data = data;
    }

    public ResponseVo2() {
    }
}
