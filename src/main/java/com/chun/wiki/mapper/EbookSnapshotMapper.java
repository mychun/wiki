package com.chun.wiki.mapper;

import com.chun.wiki.domain.EbookSnapshot;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 电子书快照表 Mapper 接口
 * </p>
 *
 * @author chun
 * @since 2022-01-06
 */
public interface EbookSnapshotMapper extends BaseMapper<EbookSnapshot> {
    void genSnapshot();
}
