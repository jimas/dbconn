package com.jimas.dbconn.api.response;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 日志统计返回类
 * @author weqinjia.liu
 * @Date 2017年3月16日
 */
public class LogStatisticsRs implements Serializable{
    
    private static final long serialVersionUID = -9119872220943773156L;
    
    private String siteSource;
    
    private Date operateDate;
    
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
    
    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LogStatisticsRs [");
        if (siteSource != null) builder.append("siteSource=").append(siteSource).append(", ");
        if (operateDate != null) builder.append("operateDate=").append(operateDate).append(", ");
        if (accessCount != null) builder.append("accessCount=").append(accessCount);
        builder.append("]");
        return builder.toString();
    }

    
   
    

}
