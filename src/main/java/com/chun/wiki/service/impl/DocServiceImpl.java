package com.chun.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chun.wiki.domain.Doc;
import com.chun.wiki.domain.DocContent;
import com.chun.wiki.mapper.DocMapper;
import com.chun.wiki.req.DocSaveReq;
import com.chun.wiki.resp.CommonResp;
import com.chun.wiki.service.DocContentService;
import com.chun.wiki.service.DocService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chun.wiki.service.WxService;
import com.chun.wiki.websocket.WebSocketServer;
import org.slf4j.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private WxService wxService;

    @Autowired
    private HttpServletRequest request;

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
    public void save(DocSaveReq docSaveReq) {
        Doc doc = new Doc();
        BeanUtils.copyProperties(docSaveReq, doc);

        if(docSaveReq.getId() != null){
            baseMapper.updateById(doc);
        }else {
            baseMapper.insert(doc);
        }

        if(docSaveReq.getContent() != null){
            DocContent docContent = new DocContent();
            docContent.setContent(docSaveReq.getContent());
            docContent.setId(doc.getId());

            if(docSaveReq.getId() != null){
                docContentService.updateById(docContent);
            }else {
                docContentService.save(docContent);
            }
        }
    }

    @Transactional //开启事务
    @Override
    public void deleteDocById(Long id) {
        baseMapper.deleteById(id);
        docContentService.removeById(id);
    }

    @Override
    public void addViewCount(Long id) {
        baseMapper.addViewCount(id);
    }

    @Override
    public void addVoteCount(Long id) {
        baseMapper.addVoteCount(id);

        Doc doc = baseMapper.selectById(id);

        String logId = MDC.get("LOG_ID");
        String token = request.getHeader("token");
        //使用websocket给前端通知点赞
        //使用异步执行，让websocket和业务做一些解耦合，提高业务方法效率
        wxService.sendInfo("【" + doc.getTitle() + "】文章被点赞了", token, logId);
    }
}
