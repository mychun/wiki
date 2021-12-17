package com.chun.wiki.service.impl;

import com.chun.wiki.domain.DocContent;
import com.chun.wiki.mapper.DocContentMapper;
import com.chun.wiki.service.DocContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文档内容 服务实现类
 * </p>
 *
 * @author chun
 * @since 2021-12-17
 */
@Service
public class DocContentServiceImpl extends ServiceImpl<DocContentMapper, DocContent> implements DocContentService {

}
