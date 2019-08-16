package com.cskaoyan.bean.spread;

import java.util.ArrayList;
import java.util.List;

public class MessageBean<T> {
    public String errmsg;
    public String errno;
    public T data;

    public MessageBean() {
    }

    public MessageBean(String errmsg, String errno, T data) {
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

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
