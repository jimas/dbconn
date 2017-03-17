package com.jimas.dbconn.api.request;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 日志统计请求类
 * @author weqinjia.liu
 * @Date 2017年3月16日
 */
public class LogStatisticsRq implements Serializable{
    
    private static final long serialVersionUID = -9119872220943773156L;

    private String siteSource;//来源系统
    
    private Integer days;//统计几天
    
    private Date startDate;//从那一天开始统计

    public String getSiteSource() {
        return siteSource;
    }

    public void setSiteSource(String siteSource) {
        this.siteSource = siteSource;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LogStatisticsRq [");
        if (siteSource != null) builder.append("siteSource=").append(siteSource).append(", ");
        if (days != null) builder.append("days=").append(days).append(", ");
        if (startDate != null) builder.append("startDate=").append(startDate);
        builder.append("]");
        return builder.toString();
    }
    
    

}
