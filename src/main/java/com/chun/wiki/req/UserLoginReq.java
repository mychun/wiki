package com.chun.wiki.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
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
@ApiModel(value="UserLoginReq对象", description="用户")
public class UserLoginReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "登录名")
    @NotEmpty(message = "【登录名】不能为空")
    private String loginName;

    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "【密码】不能为空")
    private String password;
}
