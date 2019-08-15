package com.cskaoyan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: XiaoLei
 * @Date Created in 22:25 2019/8/15
 */
@RestController
public class HelloController {

    @RequestMapping("hello")
    public String hello(){
        return "hello springboot";
    }
}
