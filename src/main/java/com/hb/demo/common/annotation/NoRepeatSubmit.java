package com.hb.demo.common.annotation;

import java.lang.annotation.*;

/**
 * 表单重复提交注解
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoRepeatSubmit {

    /**
     * 重复提交时间限制，单位毫秒 ()
     * @return
     */
    long timeout() default 3000;
}
