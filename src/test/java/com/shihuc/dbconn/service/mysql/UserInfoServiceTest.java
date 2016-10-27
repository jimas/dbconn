package com.shihuc.dbconn.service.mysql;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shihuc.dbconn.repository.entity.UserInfo;
import com.shihuc.dbconn.service.BaseTest;
import com.shihuc.dbconn.service.UserInfoService;

public class UserInfoServiceTest extends BaseTest {

    @Autowired
    private UserInfoService userInfoService;
    @Test
    public void testFindById() {
        System.out.println(userInfoService.findById(2));
    }

    @Test
    public void testCreate() {
        UserInfo userInfo=new UserInfo();
        userInfo.setAge(21);
        userInfo.setJob("架构师");
        userInfo.setUsername("jimas");
        userInfoService.create(userInfo);
    }

    @Test
    public void testUpdate() {
        fail("Not yet implemented");
    }

    @Test
    public void testDeleteById() {
        fail("Not yet implemented");
    }
    
    @Test
    public void testFindAll() {
        List<UserInfo> findAll = userInfoService.findAll();
        System.out.println(findAll);
    }
    

}
