package com.chun.wiki.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author chun
 * @since 2021-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserLoginResp对象", description="用户")
public class UserLoginResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "昵称")
    private String name;

    @ApiModelProperty(value = "token")
    private String token;
}