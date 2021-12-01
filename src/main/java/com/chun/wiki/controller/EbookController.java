package com.chun.wiki.controller;

import com.chun.wiki.domain.Ebook;
import com.chun.wiki.req.EbookReq;
import com.chun.wiki.resp.CommonResp;
import com.chun.wiki.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ebook")
public class EbookController {
    @Autowired
    private EbookService ebookService;

    @GetMapping("/list")
    //http://127.0.0.1:8880/ebook/list?name=java
    //spring boot会自动把name赋值给ebookReq.name
    public CommonResp list(EbookReq ebookReq){
        CommonResp<Object> resp = new CommonResp<>();
        resp.setContent(ebookService.list(ebookReq));
        return resp;
    }
}
