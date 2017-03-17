package com.jimas.dbconn.api.response;

import java.util.Date;
/**
 * @Description 日志统计对象
 * @author weqinjia.liu
 * @Date 2017年3月15日
 */
public class LogIpCountRs extends BaseRs{
    
    private static final long serialVersionUID = 1388703912853889468L;
    
    private String id;//siteSource + operateDate + remoteAddr
    //来源系统
    private String siteSource;
    //remoteAddr
    private String remoteAddr;
    //操作时间
    private Date operateDate;
    //访问次数
    private Long access_count;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSiteSource() {
        return siteSource;
    }
    public void setSiteSource(String siteSource) {
        this.siteSource = siteSource;
    }
    public String getRemoteAddr() {
        return remoteAddr;
    }
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }
    public Date getOperateDate() {
        return operateDate;
    }
    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }
    public Long getAccess_count() {
        return access_count;
    }
    public void setAccess_count(Long access_count) {
        this.access_count = access_count;
    } 
    
    

}
