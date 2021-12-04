package com.chun.wiki.service.impl;

import com.chun.wiki.domain.Test;
import com.chun.wiki.mapper.TestMapper;
import com.chun.wiki.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private TestMapper testMapper;

    public List<Test> list(){
        return testMapper.selectList(null);
    }
}
