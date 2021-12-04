package com.chun.wiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chun.wiki.domain.Ebook;
import com.chun.wiki.mapper.EbookMapper;
import com.chun.wiki.req.EbookReq;
import com.chun.wiki.resp.EbookResp;
import com.chun.wiki.service.EbookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chun.wiki.util.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
public class EbookServiceImpl extends ServiceImpl<EbookMapper, Ebook> implements EbookService {
    @Autowired
    private EbookMapper ebookMapper;

    public List<EbookResp> getListByEbookReq(EbookReq ebookReq){
        QueryWrapper<Ebook> queryWrapper = new QueryWrapper<>();

        if(!ObjectUtils.isEmpty(ebookReq.getName())){
            queryWrapper.like("name", ebookReq.getName());
        }

        List<Ebook> list = ebookMapper.selectList(queryWrapper);

        List<EbookResp> ebookResps = CopyUtil.copyList(list, EbookResp.class);

        return ebookResps;
    }
}
