package com.chun.wiki.service;

import com.chun.wiki.domain.Ebook;
import com.chun.wiki.domain.EbookExample;
import com.chun.wiki.mapper.EbookMapper;
import com.chun.wiki.req.EbookReq;
import com.chun.wiki.resp.EbookResp;
import com.chun.wiki.util.CopyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {
    @Autowired
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq ebookReq){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if(!ObjectUtils.isEmpty(ebookReq.getName())){
            criteria.andNameLike("%" + ebookReq.getName() + "%");
        }

        List<Ebook> list = ebookMapper.selectByExample(ebookExample);

        List<EbookResp> ebookResps = CopyUtil.copyList(list, EbookResp.class);

        return ebookResps;
    }
}
