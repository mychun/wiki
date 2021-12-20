package com.chun.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chun.wiki.domain.User;
import com.chun.wiki.mapper.UserMapper;
import com.chun.wiki.req.UserSaveReq;
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
        CommonResp commonResp = new CommonResp();

        User user = new User();
        BeanUtils.copyProperties(userSaveReq, user);

        QueryWrapper<User> queryWrapperToLoginName = new QueryWrapper<>();
        queryWrapperToLoginName.eq("login_name", user.getLoginName());
        if(baseMapper.selectCount(queryWrapperToLoginName) > 0){
            return commonResp.setSuccess(false).setMessage("该【用户名】已存在");
        }

        QueryWrapper<User> queryWrapperToName = new QueryWrapper<>();
        queryWrapperToName.eq("name", user.getName());
        if(baseMapper.selectCount(queryWrapperToName) > 0){
            return commonResp.setSuccess(false).setMessage("该【昵称】已存在");
        }

        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        baseMapper.insert(user);

        return commonResp;
    }
}
