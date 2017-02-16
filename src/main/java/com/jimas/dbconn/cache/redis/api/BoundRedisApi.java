package com.jimas.dbconn.cache.redis.api;

import java.util.List;

/**
 * @Description Bound 操作
 * @author weqinjia.liu
 * @Date 2017年2月14日
 */
public interface BoundRedisApi {
    /*============================== string boundValueOps 操作  =======================================*/
    public void set(String key,String value,long timeout);
    
    public String get(String key);
    
    public boolean delete(String key);
    /*============================== hash  boundHashOps 操作  =======================================*/
    public void updateUserCache(String table,String hashKey,String hashValue);
    
    public String findUserByCache(String table,String hashKey);
    
    public List<String> findUsersByCache(String table);
    
    public long removeUserByCache(String table,String hashKey);
    
    /*============================== list 链表  boundListOps 操作  =======================================*/
    public void leftPushListCache(String key,String value);
    
    public void rightPushListCache(String key,String value);
    
    public String leftPopListCache(String key);
    
    public String rightPopListCache(String key);
    /*============================== set  boundSetOps 操作  =======================================*/
    
    public void setAdd(String setKey,String setValue);
    
    public String setPop(String setKey);
}
