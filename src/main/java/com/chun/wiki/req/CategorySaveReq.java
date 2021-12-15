package com.chun.wiki.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author chun
 * @since 2021-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Category对象", description="")
public class CategorySaveReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父分类id")
    @NotNull(message = "父分类id不能为空")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    @NotNull(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}