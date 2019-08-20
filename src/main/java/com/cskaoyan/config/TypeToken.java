package com.cskaoyan.config;

import org.apache.shiro.authc.UsernamePasswordToken;

public class TypeToken extends UsernamePasswordToken {
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TypeToken(String username, String password, String type) {
        super(username, password);
        this.type = type;
    }
}
