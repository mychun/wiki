package com.chun.wiki.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserUpdatePassword对象", description="用户")
public class UserUpdatePassword implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.INPUT)
    @NotNull(message = "【用户ID】不能为空")
    private Long id;

    @ApiModelProperty(value = "旧密码")
    @NotNull(message = "【旧密码】不能为空")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,32}$", message = "【旧密码】至少包含 数字和英文，长度6-32")
    private String oldPassword;

    @ApiModelProperty(value = "新密码")
    @NotNull(message = "【新密码】不能为空")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,32}$", message = "【新密码】至少包含 数字和英文，长度6-32")
    private String newPassword;
}