package com.shihuc.dbconn.task;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Description 定时任务配置类
 * @author weqinjia.liu
 * @Date 2016年10月9日
 */
@Configuration
@EnableScheduling // 启用定时任务
public class SchedulingConfig {
    
    private final Logger logger = Logger.getLogger(getClass()); 
    
    /**
     * 同步测试
     */
    @Scheduled(cron = "0 0/2 * * * ?") // 每2分钟执行一次
    public void schedulerTest() {
        logger.info("SchedulingConfig.schedulerTest start");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("SchedulingConfig.schedulerTest end");
    }
}