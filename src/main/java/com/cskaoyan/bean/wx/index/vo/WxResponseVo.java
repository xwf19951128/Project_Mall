package com.cskaoyan.bean.wx.index.vo;

/**
 * @Author: XiaoLei
 * @Date Created in 13:36 2019/8/23
 */

public class WxResponseVo {

    public WxResponseVo() {
    }

    public WxResponseVo(String errmsg, int errno) {
        this.errmsg = errmsg;
        this.errno = errno;
    }

    private String errmsg;
    private int errno;

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

    @Override
    public String toString() {
        return "WxResponseVo{" +
                "errmsg='" + errmsg + '\'' +
                ", errno=" + errno +
                '}';
    }
}
