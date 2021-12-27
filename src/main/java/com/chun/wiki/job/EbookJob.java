package com.chun.wiki.job;

import com.chun.wiki.service.EbookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class EbookJob {

    @Autowired
    private EbookService ebookService;

    private static final Logger LOG = LoggerFactory.getLogger(TestJob.class);

    /**
     * 自定义cron表达式跑批
     * 只有等上一次执行完成，下一次才会在下一个时间点执行，错过就错过
     * springboot自带的定时器是同一线程的
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void cron() throws InterruptedException {
        LOG.info("更新电子书下的文档数据开始");
        Long start = System.currentTimeMillis();
        ebookService.updateEbookCountInfo();
        long end = System.currentTimeMillis();
        LOG.info("更新电子书下的文档数据结束，总耗时：{}毫秒", end - start);
    }

}
