package com.chun.wiki.service;

import com.chun.wiki.domain.Ebook;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chun.wiki.req.EbookReq;
import com.chun.wiki.resp.EbookResp;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chun
 * @since 2021-12-04
 */
public interface EbookService extends IService<Ebook> {

    List<EbookResp> getListByEbookReq(EbookReq ebookReq);
    void updateEbookCountInfo();
}
