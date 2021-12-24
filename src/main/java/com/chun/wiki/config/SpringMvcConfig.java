package com.chun.wiki.config;

import com.chun.wiki.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    //指定拦截器
    @Resource
    LoginInterceptor loginInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        //加入拦截器到到配置里
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") // **表示后面不管是什么（/demo /demo/123）都可以
                .excludePathPatterns("/wiki/user/login")
                //不拦截swagger
                .excludePathPatterns(
                        "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/error"
                );
        //下面可以加多个拦截器
    }
}
