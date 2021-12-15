package com.chun.wiki.exceptionhandle;

import com.chun.wiki.resp.CommonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理、数据预处理等
 */
@ControllerAdvice
public class GlobalException {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalException.class);

    /**
     * 校验异常统一处理
     * @param e
     * @return
     */
    //自定义 异常类型
    //BindException是校验插件spring-boot-starter-validation的异常，可以根据idea的run打印出来的日志，来拿到这个异常
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public CommonResp validExceptionHandler(BindException e) {
        CommonResp commonResp = new CommonResp();
        LOG.warn("参数校验失败：{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return commonResp;
    }

    //下面可以定义多个异常处理
}
