package com.shihuc.dbconn.cache.redis.api;

import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * redis 的操作开放接口
 * 
 * @author weqinjia.liu
 * 
 */
public interface RedisApi {

    /**
     * 通过key删除
     * 
     * @param key
     */
    public abstract long del(String... keys);

    /**
     * 批量删除
     * @param pattern
     * @return
     */
    public abstract long removePattern(String pattern);

    /**
     * 添加key value 并且设置存活时间
     * 
     * @param key
     * @param value
     * @param liveTime
     *            单位秒
     */
    public abstract Boolean set(String key, String value, long liveTime);

    /**
     * 添加key value 并且设置存活时间
     * @param key
     * @param valueObj
     * @param liveTime
     * @return
     */
    public abstract Boolean set(String key, Object valueObj, long liveTime);

    /**
     * 得到key的有效期 默认 秒
     * @param key,timeUnit
     * @return
     */
   
    public abstract  Long getExpire(String key, TimeUnit timeUnit); 
    /**
     * 添加key value
     * @param key
     * @param value
     */
    public abstract Boolean set(String key, String value);

    /**
     * 获取redis value (String)
     * 
     * @param key
     * @return
     */
    public abstract Object get(String key);

    /**
     * 通过正则匹配keys
     * 
     * @param pattern
     * @return
     */
    public abstract Set<String> getkeys(String pattern);

    /**
     * 检查key是否已经存在
     * 
     * @param key
     * @return
     */
    public abstract Boolean exists(String key);

    /**
     * 清空redis 所有数据
     * 
     * @return
     */
    public abstract String flushDB();

    /**
     * 查看redis里有多少数据
     */
    public abstract long dbSize();

    /**
     * 检查是否连接成功
     * 
     * @return
     */
    public abstract String ping();
}