package com.jimas.dbconn.api.request;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description 日志统计请求类
 * @author weqinjia.liu
 * @Date 2017年3月16日
 */
public class LogIpRq implements Serializable{
    
    private static final long serialVersionUID = -2632677264203690530L;

    private String siteSource;//来源系统
    
    private Integer size=5;//默认前5

    private Date start;//统计开始日期
    
    private Date end;//统计结束日期
    
    private List<String> removeIpList;//排除的ip

    public String getSiteSource() {
        return siteSource;
    }

    public void setSiteSource(String siteSource) {
        this.siteSource = siteSource;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public List<String> getRemoveIpList() {
        return removeIpList;
    }

    public void setRemoveIpList(List<String> removeIpList) {
        this.removeIpList = removeIpList;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LogIpRq [");
        if (siteSource != null) builder.append("siteSource=").append(siteSource).append(", ");
        if (size != null) builder.append("size=").append(size).append(", ");
        if (start != null) builder.append("start=").append(start).append(", ");
        if (end != null) builder.append("end=").append(end).append(", ");
        if (removeIpList != null) builder.append("removeIpList=").append(removeIpList);
        builder.append("]");
        return builder.toString();
    }

    
    
    
    
    
    
    

}
