package com.jimas.dbconn.api.response;

import java.io.Serializable;

/**
 * @Description 日志统计返回类
 * @author weqinjia.liu
 * @Date 2017年3月16日
 */
public class LogUrlStatisticsRs implements Serializable{
    
    private static final long serialVersionUID = 877376207110362400L;

    private String siteSource;
    private String url;
    private Integer accessCount;

    public String getSiteSource() {
        return siteSource;
    }

    public void setSiteSource(String siteSource) {
        this.siteSource = siteSource;
    }

    public Integer getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(Integer accessCount) {
        this.accessCount = accessCount;
    }
    
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LogUrlStatisticsRs [");
        if (siteSource != null) builder.append("siteSource=").append(siteSource).append(", ");
        if (url != null) builder.append("url=").append(url).append(", ");
        if (accessCount != null) builder.append("accessCount=").append(accessCount);
        builder.append("]");
        return builder.toString();
    }

    
   
    

}
