package com.chun.wiki.exceptionhandle;

/**
 * 定义业务异常信息
 */
public enum BusinessExceptionCode {
    USER_LOGIN_NAME_EXIST("【登录名】已存在"),
    USER_NAME_EXIST("【昵称】已存在"),
    USER_NO_EXIST("【用户】不存在"),
    USER_OLD_PASSWORD_ERROR("【用户旧密码】不对");

    private String desc;
    BusinessExceptionCode(String desc) { this.desc = desc;}

    public String getDesc(){return desc;}
    public void setDesc(String desc) {this.desc = desc;}
}
