package com.imyzone.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Titie:
 * Description:
 * JDK:
 * Tomcat:
 * Author: fangchenhui
 * CreateTime:2017/6/10 15:50
 * version: 1.0
 **/
@RestController
public class HelloController {

    @Value("${my.desc}")
    private String profileActive;

    @RequestMapping("/hello")
    public String hello() throws Exception {
        throw new Exception("发送错误");
    }

    @RequestMapping("/")
    public String index(){
        System.out.println("进入了controller");
        return "welcome";
    }
}
