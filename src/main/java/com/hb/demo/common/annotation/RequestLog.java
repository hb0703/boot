package com.hb.demo.common.annotation;

import com.baomidou.mybatisplus.annotation.IdType;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestLog {

    String interfaceName() default "";

    String model() default "";

    String requestUrl() default "";


    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;
}
