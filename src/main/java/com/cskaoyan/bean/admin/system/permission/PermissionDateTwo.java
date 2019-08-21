package com.cskaoyan.bean.admin.system.permission;

import java.util.List;

/**
 * @Author: XiaoLei
 * @Date Created in 11:35 2019/8/20
 */
public class PermissionDateTwo {
    String id;
    String label;
    List<PermissionDateThree> children;

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

    public List<PermissionDateThree> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionDateThree> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "PermissionDateTwo{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", children=" + children +
                '}';
    }
}
