package com.cskaoyan.bean.admin.system.permission;

/**
 * @Author: XiaoLei
 * @Date Created in 11:43 2019/8/20
 */
public class PermissionDateFour {
    private String id;
    private String label;
    private String api;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    @Override
    public String toString() {
        return "PermissionDateFour{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", api='" + api + '\'' +
                '}';
    }
}
