package com.chun.wiki.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chun.wiki.domain.DocContent;
import com.chun.wiki.req.DocSaveReq;
import com.chun.wiki.resp.CommonResp;
import com.chun.wiki.service.DocContentService;
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
@Api(description = "文章管理")
@RestController
@RequestMapping("/wiki/doc")
public class DocController {
    @Autowired
    private DocService docService;

    @Autowired
    private DocContentService docContentService;

    @ApiOperation("根据电子书id返回文档列表")
    @GetMapping("/list/{id}")
    public CommonResp getDocListForEbookId(
        @ApiParam(name = "id", value = "电子书id", required = true)
        @PathVariable Long id
    ){
        return docService.getDocListForEbookId(id);
    }

    @ApiOperation("保存文章")
    @PostMapping("/save")
    public CommonResp save(
            @ApiParam(name = "docAddReq", value = "文章对象", required = true)
            @Valid @RequestBody DocSaveReq docSaveReq
    ){
        docService.save(docSaveReq);
        CommonResp<Object> commonResp = new CommonResp<>();
        return commonResp;
    }

    @ApiOperation("删除文章")
    @DeleteMapping("/delete/{id}")
    public CommonResp delete(
            @ApiParam(name = "id", value = "文章id", required = true)
            @PathVariable Long id
    ){
        docService.deleteDocById(id);
        CommonResp<Object> commonResp = new CommonResp<>();
        return commonResp;
    }

    @ApiOperation("根据文章id获取文章内容")
    @GetMapping("/getDocContent/{id}")
    public CommonResp getDocContent(
            @ApiParam(name = "id", value = "文章id", required = true)
            @PathVariable Long id
    ){
        DocContent docContent = docContentService.getById(id);

        CommonResp commonResp = new CommonResp();
        commonResp.setContent(docContent.getContent());
        return commonResp;
    }

    @ApiOperation("文章内容阅读数+1")
    @PutMapping("/viewDocContent/{id}")
    public CommonResp viewDocContent(
            @ApiParam(name = "id", value = "文章id", required = true)
            @PathVariable Long id
    ){
        docService.addViewCount(id);

        CommonResp commonResp = new CommonResp();
        return commonResp;
    }

    @ApiOperation("文章内容点赞数+1")
    @PutMapping("/voteCount/{id}")
    public CommonResp voteCount(
            @ApiParam(name = "id", value = "文章id", required = true)
            @PathVariable Long id
    ){
        docService.addVoteCount(id);

        CommonResp commonResp = new CommonResp();
        return commonResp;
    }
}

