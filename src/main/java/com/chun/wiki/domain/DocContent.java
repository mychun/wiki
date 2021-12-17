package com.chun.wiki.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文档内容
 * </p>
 *
 * @author chun
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DocContent对象", description="文档内容")
public class DocContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文档内容id（与文档的id一样）")
    private Long id;

    @ApiModelProperty(value = "文档内容")
    private String content;


}
