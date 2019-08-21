package com.cskaoyan.bean.admin.system.permission;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 11:41 2019/8/20
 */
public class PermissionDateThree {
    String id;
    String label;
    List<PermissionDateFour> children;

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

    public List<PermissionDateFour> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionDateFour> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "PermissionDateThree{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", children=" + children +
                '}';
    }
}

