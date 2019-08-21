package com.cskaoyan.bean.wx.tools;

public class Region_wx {
    private int id; // region表的id
    private int pid;    // 行政区域父id
    private String name;    // 行政区域名称
    private int type;   // 行政区域类型 1是省，2是市，3是区县
    private int code;   // 行政区域编码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
