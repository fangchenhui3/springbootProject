package com.imyzone.util;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Titie:
 * Description:
 * JDK:
 * Tomcat:
 * Author: fangchenhui
 * CreateTime:2017/6/14 22:44
 * version: 1.0
 **/
@ControllerAdvice
class ErrorUtil {
    public static final String DEFAULT_ERROR_VIEW = "error";
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        System.out.println("-------e:"+e.getMessage());
        mav.addObject("url", req.getRequestURL());
        mav.addObject("aaa","ddd");
        System.out.println("------req："+req.getRequestURL());

        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
