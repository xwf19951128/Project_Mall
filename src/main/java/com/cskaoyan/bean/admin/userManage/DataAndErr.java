package com.cskaoyan.bean.admin.userManage;


public class DataAndErr {
    ItemAndTotal data;
    String errmsg;
    int errno;

    public ItemAndTotal getData() {
        return data;
    }

    public void setData(ItemAndTotal data) {
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
}
