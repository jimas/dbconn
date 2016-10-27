package com.shihuc.dbconn.cache.redis.service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shihuc.dbconn.cache.redis.api.RedisApi;
@Service("redisService")
public class RedisService implements RedisApi,InitializingBean {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    /**
     * @param key
     */
    public long del(final String... keys) {
        return  redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
                for (int i = 0; i < keys.length; i++) {
                    byte[] bkey = stringRedisSerializer.serialize(keys[i]);
                    result =result+ connection.del(bkey);
                }
                return result;
            }
        });
    }


    /**
     * 通过正则匹配 批量删除
     */
    @Override
    public long removePattern(String pattern) {
       final Set<String> keys = getkeys(pattern);
        if(!CollectionUtils.isEmpty(keys)){
            return redisTemplate.execute(new RedisCallback<Long>() {
                long result = 0;
                @Override
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    RedisSerializer<String> stringSerializer = redisTemplate.getStringSerializer();
                    for(String key :keys){
                        result=result+connection.del(stringSerializer.serialize(key));
                    }
                    return result;
                }
                
            });
        }
        return 0;
    }

    /**
     * @param key
     * @param value
     * @param liveTime
     */
    public Boolean set(String key, String value, long liveTime) {
       return  redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
                GenericJackson2JsonRedisSerializer valueSerializer = (GenericJackson2JsonRedisSerializer) redisTemplate.getValueSerializer();
                byte[] bkey = stringRedisSerializer.serialize(key);
                byte[] bvalue = valueSerializer.serialize(value);
                connection.set(bkey, bvalue);
                if (liveTime > 0) {
                    return  connection.expire(bkey, liveTime);
                }else{
                    return true;
                }
            }
        });
    }

    @Override
    public Boolean set(String key, Object valueObj, long liveTime) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
                GenericJackson2JsonRedisSerializer valueSerializer = (GenericJackson2JsonRedisSerializer) redisTemplate.getValueSerializer();
                byte[] bkey = stringRedisSerializer.serialize(key);
                byte[] bvalue = valueSerializer.serialize(valueObj);
                connection.set(bkey, bvalue);
                if (liveTime > 0) {
                    return  connection.expire(bkey, liveTime);
                }else{
                    return true;
                }
            }
        });
    }
    /**
     * @param key
     * @param value
     */
    public Boolean set(String key, Object value) {
        return this.set(key, value, 0L);
    }
    /**
     * @param key
     * @param value
     */
    public Boolean set(String key, String value) {
        return this.set(key, value, 0L);
    }

    /**
     * @param key
     * @return
     */
    public Object get(final String key) {
        return redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                GenericJackson2JsonRedisSerializer valueSerializer = (GenericJackson2JsonRedisSerializer) redisTemplate.getValueSerializer();
                RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
                byte[] bkey = stringRedisSerializer.serialize(key);
                return valueSerializer.deserialize(connection.get(bkey));
//              return new String(connection.get(bkey), redisCode);
            }
        });
    }
    /**
     * @param key
     * @return
     */
    public Boolean exists(final String key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> stringRedisSerializer = redisTemplate.getStringSerializer();
                byte[] bkey = stringRedisSerializer.serialize(key);
                return connection.exists(bkey);
            }
        });
    }

    /**
     * @return
     */
    public String flushDB() {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    /**
     * @return
     */
    public long dbSize() {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
    }

    /**
     * @return
     */
    public String ping() {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.ping();
            }
        });
    }

    private RedisService() {

    }

    @Override
    public Set<String> getkeys(String pattern) {
         return redisTemplate.keys(pattern+"*");
        
    }


    @Override
    public Long getExpire(String key, TimeUnit timeUnit) {
        if(StringUtils.isEmpty(timeUnit)){
            timeUnit=TimeUnit.SECONDS;
        }
        return redisTemplate.getExpire(key, timeUnit);
    }


    /**
     * 初始化设置 valueSerializer 采用GenericJackson2JsonRedisSerializer
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(om);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
    }

}
