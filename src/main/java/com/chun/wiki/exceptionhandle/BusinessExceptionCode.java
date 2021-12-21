package com.chun.wiki.exceptionhandle;

/**
 * 定义业务异常信息
 */
public enum BusinessExceptionCode {
    USER_LOGIN_NAME_EXIST("【登录名】已存在"),
    USER_NAME_EXIST("【昵称】已存在");

    private String desc;
    BusinessExceptionCode(String desc) { this.desc = desc;}

    public String getDesc(){return desc;}
    public void setDesc(String desc) {this.desc = desc;}
}
