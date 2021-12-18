package com.chun.wiki.mapper;

import com.chun.wiki.domain.Doc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 文档 Mapper 接口
 * </p>
 *
 * @author chun
 * @since 2021-12-17
 */
public interface DocMapper extends BaseMapper<Doc> {
    void addViewCount(Long id);
    void addVoteCount(Long id);
}
