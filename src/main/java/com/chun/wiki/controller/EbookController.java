package com.chun.wiki.controller;


import com.chun.wiki.req.EbookReq;
import com.chun.wiki.resp.CommonResp;
import com.chun.wiki.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
        CommonResp<Object> resp = new CommonResp<>();
        resp.setContent(ebookService.getListByEbookReq(ebookReq));
        return resp;
    }
}

