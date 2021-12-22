package com.chun.wiki.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserUpdateReq implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.INPUT)
    @NotNull(message = "【用户ID】不能为空")
    private Long id;

    @ApiModelProperty(value = "昵称")
    @NotNull(message = "【昵称】不能为空")
    private String name;
}
