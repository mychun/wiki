package com.chun.wiki.exceptionhandle;

import com.chun.wiki.resp.CommonResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 统一异常处理、数据预处理等
 */
@ControllerAdvice
//所有异常处理方法都是json返回
//如果单个方式上设置，只是该方法为json返回
@ResponseBody
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
    public CommonResp validExceptionHandler(BindException e) {
        CommonResp commonResp = new CommonResp();
        LOG.warn("参数校验失败：{}", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        commonResp.setSuccess(false);
        commonResp.setMessage(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return commonResp;
    }

    /**
     * 自定义异常统一处理
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    //添加全局异常处理流程，根据需要设置需要处理的异常，本文以MethodArgumentNotValidException为例
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public CommonResp MethodArgumentNotValidHandler(HttpServletRequest request, MethodArgumentNotValidException exception) throws Exception
    {
        //按需重新封装需要返回的错误信息
        CommonResp commonResp = new CommonResp();
        String errorMsg = "";

        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            LOG.warn("参数校验失败：{}", error.getDefaultMessage());
            errorMsg += ", " + error.getDefaultMessage();
//            ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();
//            invalidArgument.setDefaultMessage(error.getDefaultMessage());
//            invalidArgument.setField(error.getField());
//            invalidArgument.setRejectedValue(error.getRejectedValue());
//            invalidArguments.add(invalidArgument);
        }
        commonResp.setMessage(errorMsg);
//        ResultMsg resultMsg = new ResultMsg(ResultStatusCode.PARAMETER_ERROR.getErrcode(), ResultStatusCode.PARAMETER_ERROR.getErrmsg(), invalidArguments);
//        return resultMsg;

        return commonResp;
    }

    //下面可以定义多个异常处理
}
