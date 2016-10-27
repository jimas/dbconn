package com.shihuc.dbconn.service.mysql;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shihuc.dbconn.pojo.mysql.MysqlUser;
import com.shihuc.dbconn.service.BaseTest;

public class MysqlUserServiceTest extends BaseTest{

    @Autowired
    private MysqlUserService service;
    @Test
    public void testAddUser() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetUser() {
        MysqlUser user = service.getUser(1);
        System.out.println(user);
    }
    @Test
    public void testFindById() {
        MysqlUser user = service.findById(1);
        System.out.println(user);
    }
    @Test
    public void testDelteCache() {
        service.deleteFromCache(1);
    }
    @Test
    public void testTest() {
        service.test();
    }

}
