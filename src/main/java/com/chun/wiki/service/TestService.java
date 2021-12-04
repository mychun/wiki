package com.chun.wiki.service;

import com.chun.wiki.domain.Test;
import com.chun.wiki.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestMapper testMapper;

    public List<Test> list(){
        return testMapper.selectList(null);
    }
}
