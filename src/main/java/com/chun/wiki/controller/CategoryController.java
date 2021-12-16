package com.chun.wiki.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chun.wiki.domain.Category;
import com.chun.wiki.req.CategoryQueryReq;
import com.chun.wiki.req.CategorySaveReq;
import com.chun.wiki.req.PageReq;
import com.chun.wiki.resp.CommonResp;
import com.chun.wiki.resp.PageResp;
import com.chun.wiki.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chun
 * @since 2021-12-15
 */
@Api(description = "分类管理")
@RestController
@RequestMapping("/wiki/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @ApiOperation(value = "所有分类列表分页")
    @GetMapping("/list")
    //http://127.0.0.1:8880/wiki/category/list?page=1&size=2
    //pageReq里面定义的属性
    //spring boot会自动配置并赋值
    public CommonResp list(
            @Valid CategoryQueryReq categoryQueryReq){
        //@Valid就是该对象开启校验规则
        Page<Category> page = new Page<>(categoryQueryReq.getPage(), categoryQueryReq.getSize());

        categoryService.page(page, null);

        List<Category> categorys = page.getRecords();
        long total = page.getTotal();

        PageResp<Category> categoryPageResp = new PageResp<>();
        categoryPageResp.setTotal(total).setList(categorys);

        CommonResp<PageResp<Category>> commonResp = new CommonResp<>();
        commonResp.setContent(categoryPageResp);

        return commonResp;
    }
}


