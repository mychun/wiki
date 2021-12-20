package com.chun.wiki.service.impl;

import com.chun.wiki.domain.User;
import com.chun.wiki.mapper.UserMapper;
import com.chun.wiki.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
