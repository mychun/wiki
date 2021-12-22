package com.chun.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chun.wiki.domain.User;
import com.chun.wiki.exceptionhandle.BusinessException;
import com.chun.wiki.exceptionhandle.BusinessExceptionCode;
import com.chun.wiki.mapper.UserMapper;
import com.chun.wiki.req.UserSaveReq;
import com.chun.wiki.req.UserUpdatePassword;
import com.chun.wiki.resp.CommonResp;
import com.chun.wiki.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author chun
 * @since 2021-12-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public CommonResp register(UserSaveReq userSaveReq) {


        User user = new User();
        BeanUtils.copyProperties(userSaveReq, user);

        QueryWrapper<User> queryWrapperToLoginName = new QueryWrapper<>();
        queryWrapperToLoginName.eq("login_name", user.getLoginName());
        if(baseMapper.selectCount(queryWrapperToLoginName) > 0){
            throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
        }

        QueryWrapper<User> queryWrapperToName = new QueryWrapper<>();
        queryWrapperToName.eq("name", user.getName());
        if(baseMapper.selectCount(queryWrapperToName) > 0){
            throw new BusinessException(BusinessExceptionCode.USER_NAME_EXIST);
        }

        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        baseMapper.insert(user);

        CommonResp commonResp = new CommonResp();
        return commonResp;
    }

    @Override
    public void updatePassword(UserUpdatePassword userUpdatePassword) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id", userUpdatePassword.getId());
        User user = baseMapper.selectOne(userQueryWrapper);
        if (user != null) {
            String oldPassword = DigestUtils.md5DigestAsHex(userUpdatePassword.getOldPassword().getBytes());
            if(!user.getPassword().equals(oldPassword)){
                throw new BusinessException(BusinessExceptionCode.USER_OLD_PASSWORD_ERROR);
            }

            String newPassword = DigestUtils.md5DigestAsHex(userUpdatePassword.getNewPassword().getBytes());
            user.setPassword(newPassword);

            baseMapper.updateById(user);
        } else {
            throw new BusinessException(BusinessExceptionCode.USER_NO_EXIST);
        }
    }
}
