package com.chun.wiki.exceptionhandle;

/**
 * 定义业务异常信息
 */
public enum BusinessExceptionCode {
    USER_LOGIN_NAME_EXIST("【登录名】已存在"),
    USER_NAME_EXIST("【昵称】已存在"),
    USER_NO_EXIST("【用户】不存在"),
    USER_OLD_PASSWORD_ERROR("【用户旧密码】不对"),
    USER_LOGIN_ERROR("【用户名或密码】不对"),
    NO_PERMISSION("没有权限访问"),
    VOTE_REPEAT("您已点赞过");

    private String desc;
    BusinessExceptionCode(String desc) { this.desc = desc;}

    public String getDesc(){return desc;}
    public void setDesc(String desc) {this.desc = desc;}
}
