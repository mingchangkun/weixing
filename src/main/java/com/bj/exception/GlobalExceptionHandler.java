package com.bj.exception;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

//这是一个controller辅助类，常用于全局异常处理的切面类
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({RuntimeException.class,Exception.class})// 拦截返回是 json返回结果
 //   @ResponseBody
    public ModelAndView defaultErrorHandler(Exception ex){
        ModelAndView view = new ModelAndView();
        view.setViewName("exception");
        System.out.println("==================异常捕获========================");
        ex.printStackTrace();
        return view;
    }
}
