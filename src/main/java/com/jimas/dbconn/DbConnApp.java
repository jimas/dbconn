package com.jimas.dbconn;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 
 * @author weqinjia.liu
 * @date	Jan 29, 2016
 * 
 */
@SpringBootApplication(scanBasePackages={"com.jimas.dbconn"})
@PropertySources({ @PropertySource(value = "classpath:dbconn.properties")})
//@ImportResource({ "classpath:spring/cxf-client.xml" })
@EnableAsync // 为了让@Async注解能够生效 (异步调用)
public class DbConnApp 
{
    private static final Logger logger=Logger.getLogger(DbConnApp.class);
	
   	public static void main(String[] args) throws Throwable {
   	    SpringApplication.run(DbConnApp.class, args);
   	    logger.info("===============DbcConnApp start=====================");
    }
}
