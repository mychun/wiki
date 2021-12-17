package com.chun.wiki.controller;


import com.chun.wiki.req.DocAddReq;
import com.chun.wiki.resp.CommonResp;
import com.chun.wiki.service.DocService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 文档 前端控制器
 * </p>
 *
 * @author chun
 * @since 2021-12-17
 */
@Api(description = "文档管理")
@RestController
@RequestMapping("/wiki/doc")
public class DocController {
    @Autowired
    private DocService docService;

    @ApiOperation("根据电子书id返回文档列表")
    @GetMapping("/list/{id}")
    public CommonResp getDocListForEbookId(
        @ApiParam(name = "id", value = "电子书id", required = true)
        @PathVariable Long id
    ){
        return docService.getDocListForEbookId(id);
    }

    @ApiOperation("新增文档")
    @PostMapping("/add")
    public CommonResp getDocListForEbookId(
            @ApiParam(name = "docAddReq", value = "文档对象", required = true)
            @Valid @RequestBody DocAddReq docAddReq
    ){
        docService.save(docAddReq);
        CommonResp<Object> commonResp = new CommonResp<>();
        return commonResp;
    }
}

