package com.chun.wiki.controller;


import com.chun.wiki.domain.User;
import com.chun.wiki.req.UserSaveReq;
import com.chun.wiki.req.UserUpdatePassword;
import com.chun.wiki.req.UserUpdateReq;
import com.chun.wiki.resp.CommonResp;
import com.chun.wiki.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author chun
 * @since 2021-12-20
 */
@Api(description = "用户管理")
@RestController
@RequestMapping("/wiki/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "新增用户")
    @PostMapping("/save")
    public CommonResp save(
            @ApiParam(name = "userSaveReq", value = "用户对象", readOnly = true)
            @Valid @RequestBody UserSaveReq userSaveReq
    ){
        return  userService.register(userSaveReq);
    }

    @ApiOperation(value = "用户列表")
    @GetMapping("/list")
    public CommonResp list(){
        CommonResp<List<User>> commonResp = new CommonResp();

        List<User> list = userService.list(null);
        commonResp.setContent(list);
        return  commonResp;
    }

    @ApiOperation(value = "修改用户资料")
    @PutMapping("/update")
    public CommonResp update(
            @ApiParam(name = "UserUpdateReq", value = "用户对象", readOnly = true)
            @Valid @RequestBody UserUpdateReq userUpdateReq
    ){
        User user = new User();
        BeanUtils.copyProperties(userUpdateReq, user);
        userService.updateById(user);
        return  new CommonResp();
    }

    @ApiOperation(value = "修改用户密码")
    @PutMapping("/updatePassworld")
    public CommonResp updatePassworld(
            @ApiParam(name = "UserUpdateReq", value = "用户对象", readOnly = true)
            @Valid @RequestBody UserUpdatePassword userUpdateReq
    ){
        userService.updatePassword(userUpdateReq);
        return  new CommonResp();
    }
}

