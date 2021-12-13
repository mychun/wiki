package com.chun.wiki.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chun.wiki.domain.Ebook;
import com.chun.wiki.req.EbookReq;
import com.chun.wiki.resp.CommonResp;
import com.chun.wiki.resp.PageResp;
import com.chun.wiki.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chun
 * @since 2021-12-04
 */
@RestController
@RequestMapping("/wiki/ebook")
public class EbookController {
    @Autowired
    private EbookService ebookService;

    @GetMapping("/list")
    //EbookReq里面定义的属性
    //spring boot会自动配置并赋值
    public CommonResp list(EbookReq ebookReq){
        Page<Ebook> page = new Page<>(ebookReq.getPage(), ebookReq.getSize());

        ebookService.page(page, null);

        List<Ebook> ebooks = page.getRecords();
        long total = page.getTotal();

        PageResp<Ebook> ebookPageResp = new PageResp<>();
        ebookPageResp.setTotal(total).setList(ebooks);

        CommonResp<PageResp<Ebook>> commonResp = new CommonResp<>();
        commonResp.setContent(ebookPageResp);

        return commonResp;
    }
}

