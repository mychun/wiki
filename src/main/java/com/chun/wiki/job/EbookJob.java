package com.chun.wiki.job;

import com.chun.wiki.service.EbookService;
import com.chun.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EbookJob {

    @Autowired
    private EbookService ebookService;

    @Autowired
    private SnowFlake snowFlake;

    private static final Logger LOG = LoggerFactory.getLogger(EbookJob.class);

    /**
     * 自定义cron表达式跑批
     * 只有等上一次执行完成，下一次才会在下一个时间点执行，错过就错过
     * springboot自带的定时器是同一线程的
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void cron() throws InterruptedException {
        //日志流水号
        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));

        LOG.info("更新电子书下的文档数据开始");
        Long start = System.currentTimeMillis();
        ebookService.updateEbookCountInfo();
        long end = System.currentTimeMillis();
        LOG.info("更新电子书下的文档数据结束，总耗时：{}毫秒", end - start);
    }

}
