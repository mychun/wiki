package com.chun.wiki.service;

import com.chun.wiki.domain.Doc;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chun.wiki.req.DocSaveReq;
import com.chun.wiki.resp.CommonResp;

import java.util.List;

/**
 * <p>
 * 文档 服务类
 * </p>
 *
 * @author chun
 * @since 2021-12-17
 */
public interface DocService extends IService<Doc> {
    CommonResp<List<Doc>> getDocListForEbookId(Long id);

    void save(DocSaveReq docSaveReq);

    void deleteDocById(Long id);
}
