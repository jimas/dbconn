package com.shihuc.dbconn.service.mongo;


import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shihuc.dbconn.pojo.mongo.MongoUser;
import com.shihuc.dbconn.service.BaseTest;

public class MongoUserServiceTest extends BaseTest {

    @Autowired
    private MongoUserService service;
    @Test
    public void testAddUser() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetUser() {
        String username="shihuc";
        MongoUser user = service.getUser(username);
        System.out.println(user);
    }

}
