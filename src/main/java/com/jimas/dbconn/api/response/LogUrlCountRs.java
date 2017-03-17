package com.jimas.dbconn.api.response;

import java.util.Date;
/**
 * @Description 日志统计对象
 * @author weqinjia.liu
 * @Date 2017年3月15日
 */
public class LogUrlCountRs extends BaseRs{
    
    
    private static final long serialVersionUID = -2282106759493207197L;
    private String id;//siteSource +url+ operateDate+remoteAddr
    //来源系统
    private String siteSource;
    //url
    private String url;
    //remoteAddr
    private String remoteAddr;
    //操作时间
    private Date operateDate;
    //访问次数
    private Long access_count;
    
    public String getSiteSource() {
        return siteSource;
    }
    public void setSiteSource(String siteSource) {
        this.siteSource = siteSource;
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
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getRemoteAddr() {
        return remoteAddr;
    }
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    
    

}
