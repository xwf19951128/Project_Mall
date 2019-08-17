package com.cskaoyan.bean.mall.region;

import java.util.List;

public class City {
    private List<District> children;
    private int code;
    private int id;
    private  String name;
    private byte type;

    public List<District> getChildren() {
        return children;
    }

    public void setChildren(List<District> children) {
        this.children = children;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }
}
