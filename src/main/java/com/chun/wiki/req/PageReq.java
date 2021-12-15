package com.chun.wiki.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Accessors(chain = true)
public class PageReq implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页码")
    //配置校验规则
    @NotNull(message = "【页码】不能为空")
    private Long page;

    @ApiModelProperty(value = "每页条数")
    @NotNull(message = "【每页条数】不能为空")
    private Long size;
}
