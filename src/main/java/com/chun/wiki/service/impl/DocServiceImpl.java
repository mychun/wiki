package com.chun.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chun.wiki.domain.Doc;
import com.chun.wiki.mapper.DocMapper;
import com.chun.wiki.resp.CommonResp;
import com.chun.wiki.service.DocService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public CommonResp<List<Doc>> getDocListForEbookId(Long id) {
        QueryWrapper<Doc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ebook_id", id);
        List<Doc> docs = baseMapper.selectList(queryWrapper);

        CommonResp<List<Doc>> commonResp = new CommonResp<>();
        commonResp.setContent(docs);
        return commonResp;
    }
}
