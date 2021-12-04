package com.chun.wiki.service.impl;

import com.chun.wiki.domain.Test;
import com.chun.wiki.mapper.TestMapper;
import com.chun.wiki.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chun
 * @since 2021-12-04
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

}
