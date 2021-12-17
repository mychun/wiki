package com.chun.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chun.wiki.domain.Doc;
import com.chun.wiki.domain.DocContent;
import com.chun.wiki.mapper.DocMapper;
import com.chun.wiki.req.DocAddReq;
import com.chun.wiki.resp.CommonResp;
import com.chun.wiki.service.DocContentService;
import com.chun.wiki.service.DocService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private DocContentService docContentService;

    @Override
    public CommonResp<List<Doc>> getDocListForEbookId(Long id) {
        QueryWrapper<Doc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ebook_id", id);
        List<Doc> docs = baseMapper.selectList(queryWrapper);

        CommonResp<List<Doc>> commonResp = new CommonResp<>();
        commonResp.setContent(docs);
        return commonResp;
    }

    @Transactional
    @Override
    public void save(DocAddReq docAddReq) {
        Doc doc = new Doc();
        BeanUtils.copyProperties(docAddReq, doc);
        baseMapper.insert(doc);

        if(docAddReq.getContent() != null){
            DocContent docContent = new DocContent();
            docContent.setContent(docAddReq.getContent());
            docContent.setId(doc.getId());
            docContentService.save(docContent);
        }
    }
}
