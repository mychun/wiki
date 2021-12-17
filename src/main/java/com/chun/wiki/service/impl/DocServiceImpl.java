package com.chun.wiki.service.impl;

import com.chun.wiki.domain.Doc;
import com.chun.wiki.mapper.DocMapper;
import com.chun.wiki.service.DocService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文档 服务实现类
 * </p>
 *
 * @author chun
 * @since 2021-12-17
 */
@Service
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc> implements DocService {

}
