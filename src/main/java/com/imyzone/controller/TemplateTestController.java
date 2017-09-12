package com.imyzone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Titie:
 * Description:
 * JDK:
 * Tomcat:
 * Author: fangchenhui
 * CreateTime:2017/6/11 11:03
 * version: 1.0
 **/
@Controller
public class TemplateTestController {


    @RequestMapping("/test2")
    public String templateTest(ModelMap map){

        map.addAttribute("host","http://www.imyzone.com");
        return "index";
    }
}
