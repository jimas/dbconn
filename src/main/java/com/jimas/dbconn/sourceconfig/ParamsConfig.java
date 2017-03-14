package com.jimas.dbconn.sourceconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParamsConfig {
    @Value("${result.mvcInterceptor.exclude.path}")
    private String[] mvcInterceptorExcludePath;
    @Value("${site.source}")
    private String siteSource;
    @Value("${rest.key}")
    private String restKey;//rest 接口 key
    @Value("${rest.url}")
    private String restBaseUrl;//rest 接口 base url
    
    public String[] getMvcInterceptorExcludePath() {
        return mvcInterceptorExcludePath;
    }
    public String getSiteSource() {
        return siteSource;
    }
    public String getRestKey() {
        return restKey;
    }
    public String getRestBaseUrl() {
        return restBaseUrl;
    }
    
    
    
}
