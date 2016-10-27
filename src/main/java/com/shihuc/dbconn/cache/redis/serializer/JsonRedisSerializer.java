package com.shihuc.dbconn.cache.redis.serializer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;
import com.alibaba.fastjson.JSON;
/**
 * @Description 自定义json解析器
 * @author weqinjia.liu
 * @Date 2016年10月25日
 * @param <T>
 */
public class JsonRedisSerializer implements RedisSerializer<Object> {

    private StringRedisTemplate template;
    
    public JsonRedisSerializer(StringRedisTemplate template) {
        super();
        Assert.notNull(template, "StringRedisTemplate must not be null!");
        this.template = template;
    }

    @Override
    public byte[] serialize(Object source) throws SerializationException {
        if (source == null) {
            return  new byte[0];
        }
        try {
            return template.getStringSerializer().serialize(JSON.toJSONString(source));
        } catch (Exception e) {
            throw new SerializationException("Could not write JSON: " + e.getMessage(), e);
        }
    }

    @Override
    public Object deserialize(byte[] data) throws SerializationException {
        if (data==null||data.length==0) {
            return  null;
        }
        try {
            String value = template.getStringSerializer().deserialize(data);
            return  JSON.parseObject(value,Object.class);
        } catch (Exception ex) {
            throw new SerializationException("Could not parse JSON: " + ex.getMessage(), ex);
        }
    }

}
