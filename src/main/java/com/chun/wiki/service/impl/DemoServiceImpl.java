package com.chun.wiki.service.impl;

import com.chun.wiki.domain.Demo;
import com.chun.wiki.mapper.DemoMapper;
import com.chun.wiki.service.DemoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Demo> implements DemoService {

}
