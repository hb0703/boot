package com.hb.demo.intercepter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 配置拦截器
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public AuthInterceptor getAuthInterceptor() {
        return new AuthInterceptor();
    }

    @Bean
    public RepeatSubmitInterceptor getRepeatSubmitInterceptor() {
        return new RepeatSubmitInterceptor();
    }

    @Bean
    public CrossInterceptor getCrossInterceptor() {
        return new CrossInterceptor();
    }

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(getAuthInterceptor())
                .addPathPatterns("/**") // 配置拦截路径（所有路径都拦截）
                .excludePathPatterns("/test"); // 配置排除的路径

        registry.addInterceptor(getRepeatSubmitInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(getCrossInterceptor())
                // 拦截所有请求
                .addPathPatterns("/**");

    }
}
