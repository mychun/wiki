package com.chun.wiki.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 文档
 * </p>
 *
 * @author chun
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Doc对象", description="文档")
public class DocAddReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "电子书id")
    @NotNull(message = "【电子书id】不能为空")
    private Long ebookId;

    @ApiModelProperty(value = "父id(最顶级为0)")
    private Long parentId;

    @ApiModelProperty(value = "标题")
    @NotBlank(message = "【标题】不能为空")
    private String title;

    @ApiModelProperty(value = "文档内容")
    private String content;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "阅读数")
    private Integer viewCount;

    @ApiModelProperty(value = "点赞数")
    private Integer voteCount;


}