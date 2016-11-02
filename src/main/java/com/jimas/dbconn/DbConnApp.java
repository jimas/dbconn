package com.jimas.dbconn;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * 
 * @author weqinjia.liu
 * @date	Jan 29, 2016
 * 
 */
@SpringBootApplication
@PropertySources({ @PropertySource(value = "classpath:dbconn.properties")})

public class DbConnApp 
{
    private static final Logger logger=Logger.getLogger(DbConnApp.class);
	
   	public static void main(String[] args) throws Throwable {
   	    SpringApplication.run(DbConnApp.class, args);
   	    logger.info("===============DbcConnApp start=====================");
    }
}
