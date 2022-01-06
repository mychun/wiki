package com.chun.wiki.service.impl;

import com.chun.wiki.domain.EbookSnapshot;
import com.chun.wiki.mapper.EbookSnapshotMapper;
import com.chun.wiki.service.EbookSnapshotService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 电子书快照表 服务实现类
 * </p>
 *
 * @author chun
 * @since 2022-01-06
 */
@Service
public class EbookSnapshotServiceImpl extends ServiceImpl<EbookSnapshotMapper, EbookSnapshot> implements EbookSnapshotService {

    @Override
    public void genSnapshot() {
        baseMapper.genSnapshot();
    }
}
