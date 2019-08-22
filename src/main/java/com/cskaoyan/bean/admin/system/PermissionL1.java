package com.cskaoyan.bean.admin.system;

import java.util.List;

public class PermissionL1 {
    List<PermissionL2> children;
    String label;
    String id;

    public List<PermissionL2> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionL2> children) {
        this.children = children;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
