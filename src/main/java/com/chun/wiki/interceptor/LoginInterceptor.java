package com.chun.wiki.interceptor;

import com.alibaba.fastjson.JSON;
import com.chun.wiki.exceptionhandle.BusinessException;
import com.chun.wiki.exceptionhandle.BusinessExceptionCode;
import com.chun.wiki.resp.UserLoginResp;
import com.chun.wiki.util.LoginUserContext;
import com.chun.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器：Spring框架特有的，常用于登录校验，权限校验，请求日志打印
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(LoginInterceptor.class);

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private SnowFlake snowFlake;

    //在进到Controller控制器之前，开始拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 增加日志流水号
        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));

        // 打印请求信息
        LOG.info("------------- LoginInterceptor 开始 -------------");
        long startTime = System.currentTimeMillis();
        request.setAttribute("requestStartTime", startTime);

        // OPTIONS请求不做校验,
        // 前后端分离的架构, 前端会发一个OPTIONS请求先做预检, 对预检请求不做校验
        if(request.getMethod().toUpperCase().equals("OPTIONS")){
            return true;
        }

        String path = request.getRequestURL().toString();
        LOG.info("接口登录拦截：，path：{}", path);

        //获取header的token参数
        String token = request.getHeader("token");
        LOG.info("登录校验开始，token：{}", token);
        if (token == null || token.isEmpty()) {
            LOG.info( "token为空，请求被拦截" );
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            throw new BusinessException(BusinessExceptionCode.NO_PERMISSION);
            //            return false;
        }
        Object object = redisTemplate.opsForValue().get(token);
        if (object == null) {
            LOG.warn( "token无效，请求被拦截" );
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            throw new BusinessException(BusinessExceptionCode.NO_PERMISSION);
//            return false;
        } else {
            LOG.info("已登录：{}", object);
            //把获取的信息存在ThreadLocal线程里
            //如果开启多线程，其它线程就访问不到该线程的内容
            UserLoginResp userLoginResp = new UserLoginResp();
            BeanUtils.copyProperties(object, userLoginResp);
            userLoginResp.setToken(token);
            LoginUserContext.setUser(userLoginResp);
            return true;
        }
    }
}
