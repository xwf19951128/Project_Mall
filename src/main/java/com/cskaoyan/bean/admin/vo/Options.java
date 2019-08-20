package com.cskaoyan.bean.admin.vo;

/**
 * 需要返回一个value和label属性的javabean
 */
public class Options {
    private int value;

    private String label;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
