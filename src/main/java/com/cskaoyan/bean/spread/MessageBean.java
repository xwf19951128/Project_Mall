package com.cskaoyan.bean.spread;

import java.util.ArrayList;
import java.util.List;

public class MessageBean<T> {
    public String errmsg;
    public int errno;
    public T data;

    public MessageBean() {
    }

    public MessageBean(String errmsg, int errno, T data) {
        this.errmsg = errmsg;
        this.errno = errno;
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
