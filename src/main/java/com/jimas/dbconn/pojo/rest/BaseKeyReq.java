package com.jimas.dbconn.pojo.rest;

import java.io.Serializable;


public class BaseKeyReq<T> implements Serializable{

    private static final long serialVersionUID = 5629482414227820258L;

    private T siteSource;

    public T getSiteSource() {
        return siteSource;
    }

    public void setSiteSource(T siteSource) {
        this.siteSource = siteSource;
    }

    
    

}
