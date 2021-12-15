package com.chun.wiki.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chun.wiki.domain.Ebook;
import com.chun.wiki.req.EbookReq;
import com.chun.wiki.req.EbookVoReq;
import com.chun.wiki.resp.CommonResp;
import com.chun.wiki.resp.PageResp;
import com.chun.wiki.service.EbookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chun
 * @since 2021-12-04
 */
@Api("Ebook管理")
@RestController
@RequestMapping("/wiki/ebook")
public class EbookController {
    @Autowired
    private EbookService ebookService;


    @ApiOperation(value = "所有文档列表")
    @GetMapping("/list")
    //EbookReq里面定义的属性
    //spring boot会自动配置并赋值
    public CommonResp list(
            EbookReq ebookReq){
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

    @ApiOperation(value = "新增文档")
    @PostMapping("/save")
    public CommonResp save(
        @ApiParam(name = "Ebook", value = "文档对象", readOnly = true)
        @RequestBody EbookVoReq ebookVoReq
    ){
        Ebook ebook = new Ebook();
        BeanUtils.copyProperties(ebookVoReq, ebook);
        boolean result = ebookService.save(ebook);

        CommonResp<Object> commonResp = new CommonResp<>();
        if (!result){
            commonResp.setSuccess(false);
            commonResp.setMessage("新增文档失败");
        }

        return commonResp;
    }

    @ApiOperation(value = "根绝id修改文档")
    @PostMapping("/update")
    public CommonResp update(
            @ApiParam(name = "Ebook", value = "文档对象", readOnly = true)
            @RequestBody Ebook ebook
    ){
        boolean result = ebookService.updateById(ebook);

        CommonResp<Object> commonResp = new CommonResp<>();
        if (!result){
            commonResp.setSuccess(false);
            commonResp.setMessage("修改文档失败");
        }

        return commonResp;
    }

    @ApiOperation(value = "根绝id删除文档")
    @DeleteMapping("/deleted")
    public CommonResp deleted(
            @ApiParam(name = "id", value = "文档id", readOnly = true)
            Long id
    ){
        boolean result = ebookService.removeById(id);

        CommonResp<Object> commonResp = new CommonResp<>();
        if (!result){
            commonResp.setSuccess(false);
            commonResp.setMessage("删除文档失败");
        }

        return commonResp;
    }
}

