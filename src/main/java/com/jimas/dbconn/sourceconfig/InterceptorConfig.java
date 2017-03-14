package com.jimas.dbconn.sourceconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.jimas.dbconn.interceptor.MvcResultInterceptor;
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private  MvcResultInterceptor mvcResultInterceptor;
    @Autowired
    private  ParamsConfig paramsConfig;

    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
        InterceptorRegistration ir = registry.addInterceptor(mvcResultInterceptor);
        ir.addPathPatterns("/**");
        ir.excludePathPatterns(paramsConfig.getMvcInterceptorExcludePath());
        
        super.addInterceptors(registry);
    }
}
