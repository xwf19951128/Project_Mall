package com.cskaoyan.bean.admin.system;

import java.util.List;

public class PermissionL2 {
    List<PermissionL3> children;
    String id;
    String label;

    public List<PermissionL3> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionL3> children) {
        this.children = children;
    }

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
}
