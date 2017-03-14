package com.jimas.dbconn.pojo.rest;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class LogPojo implements Serializable{
    
    private static final long serialVersionUID = 3666231252419518481L;
    
    private String url;
    private String httpMethod;
    private String remoteAddr;
    private String classMethod;
    private String args;
    private Map parameters;
    
    private Date operateDate;
    
    private String siteSource;
    
    public Date getOperateDate() {
        return operateDate;
    }
    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getHttpMethod() {
        return httpMethod;
    }
    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }
    public String getRemoteAddr() {
        return remoteAddr;
    }
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }
    public String getClassMethod() {
        return classMethod;
    }
    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }
    
    
    public String getArgs() {
        return args;
    }
    public void setArgs(String args) {
        this.args = args;
    }
    public Map getParameters() {
        return parameters;
    }
    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }
    public String getSiteSource() {
        return siteSource;
    }
    public void setSiteSource(String siteSource) {
        this.siteSource = siteSource;
    }
}
