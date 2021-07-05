package com.gyh.base.job;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务 demo
 *
 * @author guoyanhong5
 * @date 2021-03-01 18:45
 */
//@Component
public class SomeJob {
    /**
     * 每10s 打印一句消息
     *
     * @author guoyanhong5
     * @date 2021/3/1 18:48
     */
    @Scheduled(fixedRate = 10000)
    public void printMsg() {
        System.err.println(System.currentTimeMillis());
    }

    @Scheduled(cron = "0/20 * * * * ? ")
    public void otherJob() {
        System.out.println("other job");
    }
}
