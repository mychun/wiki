package com.chun.wiki.controller;


import com.chun.wiki.domain.Test;
import com.chun.wiki.mapper.TestMapper;
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
@RequestMapping("/wiki/test")
public class TestController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello world1";
    }
}

