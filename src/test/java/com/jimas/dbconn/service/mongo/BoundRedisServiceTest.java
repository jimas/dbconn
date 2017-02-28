package com.jimas.dbconn.service.mongo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jimas.dbconn.api.UserInfoApi;
import com.jimas.dbconn.cache.redis.api.BoundRedisApi;
import com.jimas.dbconn.pojo.User;
import com.jimas.dbconn.repository.entity.UserInfo;
import com.jimas.dbconn.service.BaseTest;
import com.jimas.dbconn.util.GsonUtil;

public class BoundRedisServiceTest extends BaseTest {

    @Autowired
    private BoundRedisApi service;
    @Autowired
    private UserInfoApi userInfoApi;
    
    GsonUtil gsonUtil = new GsonUtil();
    @Test
    public void testSet() {
        String key="jimas_User";
        User user=new User();
        user.setAge(10);
        user.setJob("java工程师");
        user.setUsername("jimas");
        String value=gsonUtil.toJsonString(user);
        service.set(key, value, 100);
    }

    @Test
    public void testGet() {
        String key="jimas_User";
        String value = service.get(key);
        User user = (User) gsonUtil.parseJson(value, User.class);
        System.out.println(user);
    }

    @Test
    public void testDelete() {
        String key="jimas_User";
        boolean delete = service.delete(key);
        System.out.println(delete);
    }
    @Test
    public void testUpdateUserCache(){
        List<UserInfo> list = userInfoApi.findAll();
        for (UserInfo userInfo : list) {
            String hashValue=gsonUtil.toJsonString(userInfo);
            String hashKey=userInfo.getId().toString();
            service.updateUserCache("User", hashKey, hashValue);
        }
    }
    @Test
    public void testFindUserByCache(){
        String hashKey="2";
        String json = service.findUserByCache("User", hashKey);
        UserInfo obj= (UserInfo) gsonUtil.parseJson(json, UserInfo.class);
        System.out.println(obj);
    }
    @Test
    public void testFindUsersByCache(){
        List<String> json = service.findUsersByCache("User");
        List<UserInfo> list=new ArrayList<UserInfo>();
        for (String string : json) {
            UserInfo obj= (UserInfo) gsonUtil.parseJson(string, UserInfo.class);
            list.add(obj);
        }
        System.out.println(list);
    }
    @Test
    public void testRemoveUserByCache(){
        String hashKey="2";
        service.removeUserByCache("User", hashKey);
    }
    
    @Test
    public void testLeftPushListCache(){
        List<UserInfo> list = userInfoApi.findAll();
        String key="UserInfo";
        for (UserInfo userInfo : list) {
            service.leftPushListCache(key, gsonUtil.toJsonString(userInfo));
        }
    }
    @Test
    public void testRightPushListCache(){
        List<UserInfo> list = userInfoApi.findAll();
        String key="UserInfo";
        for (UserInfo userInfo : list) {
            service.rightPushListCache(key, gsonUtil.toJsonString(userInfo));
        }
    }
    @Test
    public void testLeftPopListCache(){
        String key="UserInfo";
        String lestJson = service.leftPopListCache(key);
        UserInfo userInfo= (UserInfo) gsonUtil.parseJson(lestJson, UserInfo.class);
        System.out.println(userInfo);
    }
    @Test
    public void testRightPopListCache(){
        String key="UserInfo";
        String lestJson = service.rightPopListCache(key);
        UserInfo userInfo= (UserInfo) gsonUtil.parseJson(lestJson, UserInfo.class);
        System.out.println(userInfo);
    }
    @Test
    public void testSetAdd(){
        List<UserInfo> list = userInfoApi.findAll();
        String setKey="UserSet";
        for (UserInfo userInfo : list) {
            String setValue=gsonUtil.toJsonString(userInfo);
            service.setAdd(setKey, setValue);
        }
        for (UserInfo userInfo : list) {
            String setValue=gsonUtil.toJsonString(userInfo);
            service.setAdd(setKey, setValue);
        }
    }
    
    @Test
    public void testSetPop(){
        String setKey="UserSet";
        String json = service.setPop(setKey);
        System.out.println(gsonUtil.parseJson(json, UserInfo.class));
    }

}
