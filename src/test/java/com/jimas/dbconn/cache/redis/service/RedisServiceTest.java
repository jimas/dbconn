package com.jimas.dbconn.cache.redis.service;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jimas.dbconn.pojo.mysql.MysqlUser;
import com.jimas.dbconn.repository.entity.UserInfo;
import com.jimas.dbconn.service.BaseTest;
import com.jimas.dbconn.service.UserInfoService;

public class RedisServiceTest extends BaseTest {

    @Autowired
    private RedisService service;
    @Autowired
    private UserInfoService userService;
    @Test
    public void testDel() {
        System.out.println(service.del("test1","test2"));
    }
    @Test
    public void testRemovePattern() {
        String pattern="test";
        System.out.println(service.removePattern(pattern));
    }

    @Test
    public void testSetStringStringLong() {
        String key="test1";
        String value="value1中国";
        System.out.println(service.set(key, value,0));
        String x = (String) service.get(key);
        System.out.println(x);
    }

    @Test
    public void testSetStringObjectLong() {
        String key="userInfo";
        UserInfo findById = userService.findById(1);
        System.out.println(service.set(key, findById,0));
        UserInfo x = (UserInfo) service.get(key);
        System.out.println(x);
    }

    @Test
    public void testSetStringString() {
        String key="test2";
        String value="value2中国";
        System.out.println(service.set(key, value));
    }

    @Test
    public void testGet() {
        String key="com.jimas.dbconn.controller.UserInfoController.findFromMongodb()";
        System.out.println(service.get(key));
    }

    @Test
    public void testExists() {
        String key="test2";
        System.out.println(service.exists(key));
    }

    @Test
    public void testFlushDB() {
        System.out.println(service.flushDB());
    }

    @Test
    public void testDbSize() {
        System.out.println(service.dbSize());
    }

    @Test
    public void testPing() {
        System.out.println(service.ping());
    }
    @Test
    public void testGetExpire() {
        String key="com.jimas.dbconn.controller.UserInfoController.findFromMongodb()";
        TimeUnit timeUnit=TimeUnit.SECONDS;
        System.out.println(service.getExpire(key, timeUnit));
    }

    @Test
    public void testGetkeys() {
        String pattern="test";
        Set<String> keys = service.getkeys(pattern);
        System.out.println(keys);
    }
    @Test
    public void testList(){
        String key="userInfoList";
        List<UserInfo> findAll = userService.findAll();
        System.out.println(service.set(key, findAll, 0));
        @SuppressWarnings("unchecked")
        List<UserInfo> x = (List<UserInfo>) service.get(key);
        System.out.println(x);
    }
    @Test
    public void testReload() {
        String key="com.shihuc.dbconn.controller.UserInfoController.findAll()";
        @SuppressWarnings("unchecked")
        List<UserInfo>  list = (List<UserInfo>) service.get(key);
        System.out.println(list);
    }
    @Test
    public void testGetMysql() {
        String key="userInfo_1";
        MysqlUser list = (MysqlUser) service.get(key);
        System.out.println(list.getUsername());
    }
    @Test
    public void testHashMap(){
        String key="map1";
        List<UserInfo> findAll = userService.findAll();
        HashMap<String,UserInfo> value=new HashMap<String,UserInfo>();
        for (UserInfo userInfo : findAll) {
            value.put(userInfo.getId()+"", userInfo);
        }
        System.out.println(service.set(key, value));
        HashMap<String,UserInfo> x = (HashMap<String, UserInfo>) service.get(key);
        System.out.println(x.get("1"));
    }

}
