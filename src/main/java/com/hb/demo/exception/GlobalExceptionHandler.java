package com.hb.demo.exception;

import com.hb.demo.api.R;
import com.hb.demo.api.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice //该注解定义全局异常处理类
//@ControllerAdvice(basePackages ="com.hb.demo.controller") 可指定包
@ResponseBody
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value=Exception.class) //该注解声明异常处理方法
    public R<String> exceptionHandler(HttpServletRequest request, Exception e){
        log.info(e.getMessage());
        //对于自定义异常的处理
        if(e instanceof CommonBusinessException) {
            return R.fail("业务异常");
        }if(e instanceof ArithmeticException){
            return R.fail("计算错误异常！");
        }else {
            return R.fail(ResultCode.INTERNAL_SERVER_ERROR.getMessage());
        }
    }
}
