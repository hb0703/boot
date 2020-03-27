package com.hb.demo.intercepter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hb.demo.common.annotation.NoRepeatSubmit;
import com.hb.demo.common.utils.ObjectUtils;
import com.hb.demo.exception.RepeatSubmitException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 表单重复提交拦截器拦截器
 */
@Component
@AllArgsConstructor
@NoArgsConstructor
public class RepeatSubmitInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        NoRepeatSubmit noRepeatSubmit = handlerMethod.getMethodAnnotation(NoRepeatSubmit.class);
        if(Objects.isNull(noRepeatSubmit)) {
            return Boolean.TRUE;
        }
        long timeout = noRepeatSubmit.timeout();
        if(timeout < 3000L) {
            timeout = 3000;
        }
        String formId = request.getParameter("form_id");
        if(StringUtils.isEmpty(formId)) {
            throw new RepeatSubmitException();
        }
        Object o = redisTemplate.opsForValue().get(formId);
        if(Objects.isNull(o)) {
            redisTemplate.boundValueOps(formId).set(formId, timeout, TimeUnit.MILLISECONDS);
            return Boolean.TRUE;
        }
        redisTemplate.boundValueOps(formId).set(formId, timeout, TimeUnit.MILLISECONDS);
        throw new RepeatSubmitException();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
