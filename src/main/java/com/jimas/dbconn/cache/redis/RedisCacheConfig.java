package com.jimas.dbconn.cache.redis;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * redis 缓存配置; 注意：RedisCacheConfig这里也可以不用继承：CachingConfigurerSupport，也就是直接一个普通的Class就好了； 这里主要我们之后要重新实现
 * key的生成策略，只要这里修改KeyGenerator，其它位置不用修改就生效了。 普通使用普通类的方式的话，那么在使用@Cacheable的时候还需要指定KeyGenerator的名称;这样编码的时候比较麻烦。
 *
 * @author weqinjia.liu
 * @version v.0.1
 */
@Configuration
@EnableCaching
// 启用缓存，这个注解很重要；
public class RedisCacheConfig extends CachingConfigurerSupport {

    /**
     * 缓存管理器.
     * 
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("test", 60L);
        rcm.setExpires(map);
        return rcm;
    }

    @Bean
    public KeyGenerator wiselyKeyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append("." + method.getName() + "(");
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                sb.append(")");
                return sb.toString();
            }
        };

    }

    /**
     * redis模板操作类,类似于jdbcTemplate的一个类; 虽然CacheManager也能获取到Cache对象，但是操作起来没有那么灵活；
     * 这里在扩展下：RedisTemplate这个类不见得很好操作，我们可以在进行扩展一个我们 自己的缓存类，比如：RedisStorage类;
     *
     * @param factory : 通过Spring进行注入，参数在application.properties进行配置；
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // JsonRedisSerializer jsonRedisSerializer = new JsonRedisSerializer(template);
        // template.setValueSerializer(jsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
