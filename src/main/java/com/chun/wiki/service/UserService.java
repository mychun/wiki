package com.chun.wiki.service;

import com.chun.wiki.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chun.wiki.req.UserLoginReq;
import com.chun.wiki.req.UserSaveReq;
import com.chun.wiki.req.UserUpdatePassword;
import com.chun.wiki.resp.CommonResp;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author chun
 * @since 2021-12-20
 */
public interface UserService extends IService<User> {
    CommonResp register(UserSaveReq userSaveReq);
    void updatePassword(UserUpdatePassword userUpdatePassword);

    void login(UserLoginReq userLoginReq);
}
