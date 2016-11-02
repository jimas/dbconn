package com.jimas.dbconn.cache.redis.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisTest {
    
    private Jedis jedis;
    
    @Before
    public void before() {
        jedis = new Jedis("192.168.80.129");
    }
    
    /**
     * 简单添加
     * 
     */
    @Test
    public void test1(){
        String key="hm1";
        Map<String, String> hash=new HashMap<String, String>();
        for(int i=0;i<3;i++){
            hash.put("key"+i, "value"+i);
        }
        jedis.hmset(key, hash);
    }
}
