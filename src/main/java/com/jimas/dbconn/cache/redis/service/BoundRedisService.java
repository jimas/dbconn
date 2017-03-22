package com.jimas.dbconn.cache.redis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.jimas.dbconn.cache.redis.api.BoundRedisApi;
import com.jimas.dbconn.constant.Constant;

@Service
public class BoundRedisService implements BoundRedisApi {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public void set(String key, String value,long timeout) {
        redisTemplate.boundValueOps(key).set(value);
        if(timeout>0){
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }else{
            redisTemplate.expire(key, Constant.DEFAULTTIMEOUT, TimeUnit.SECONDS);
        }
    }

    @Override
    public String get(String key) {
        String value = redisTemplate.boundValueOps(key).get();
        if(StringUtils.isEmpty(value)){
            this.delete(key);
        }
        return value;
    }

    @Override
    public boolean delete(String key) {
        Assert.notNull(key,"key 不能为空！");
        redisTemplate.delete(key);
        return true;
    } 

    @Override
    public void updateUserCache(String table, String hashKey, String hashValue) {
        redisTemplate.boundHashOps(table).put(hashKey, hashValue);
    }

    @Override
    public String findUserByCache(String table, String hashKey) {
        BoundHashOperations<String, Object, Object> boundHashOps = redisTemplate.boundHashOps(table);
        return  (String) boundHashOps.get(hashKey);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<String> findUsersByCache(String table) {
        return new ArrayList(redisTemplate.boundHashOps(table).values());
    }
    @Override
    public long removeUserByCache(String table, String hashKey) {
        return redisTemplate.boundHashOps(table).delete(hashKey);
    }
    @Override
    public void leftPushListCache(String key, String value) {
        redisTemplate.boundListOps(key).leftPush(value);
        redisTemplate.expire(key, Constant.DEFAULTTIMEOUT, TimeUnit.SECONDS);
    }

    @Override
    public void rightPushListCache(String key, String value) {
        redisTemplate.boundListOps(key).rightPush(value);
        redisTemplate.expire(key, Constant.DEFAULTTIMEOUT, TimeUnit.SECONDS);
    }

    @Override
    public String leftPopListCache(String key) {
        return redisTemplate.boundListOps(key).leftPop();
    }
    @Override
    public String rightPopListCache(String key) {
        return redisTemplate.boundListOps(key).rightPop();
    }

    @Override
    public void setAdd(String setKey, String setValue) {
        redisTemplate.boundSetOps(setKey).add(setValue);
    }

    @Override
    public String setPop(String setKey) {
        return redisTemplate.boundSetOps(setKey).pop();
    }


}
