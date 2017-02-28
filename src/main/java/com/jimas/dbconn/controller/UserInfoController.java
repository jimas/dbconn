package com.jimas.dbconn.controller;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jimas.dbconn.pojo.mongo.MongoUser;
import com.jimas.dbconn.pojo.mysql.MysqlUser;
import com.jimas.dbconn.repository.entity.UserInfo;
import com.jimas.dbconn.service.UserInfoService;
import com.jimas.dbconn.service.mongo.MongoUserService;
import com.jimas.dbconn.service.mysql.MysqlUserService;
@Controller
public class UserInfoController extends BaseController {

    private static final Logger logger=Logger.getLogger(UserInfoController.class);
    @Autowired
    private MysqlUserService userService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private MongoUserService mongoUserService;
    @RequestMapping("/test")
    public @ResponseBody String test() {
        MysqlUser loaded = userService.findById(1);
        logger.info("loaded=" + loaded);
        MysqlUser cached = userService.findById(1);
        logger.info("cached=" + cached);
        loaded = userService.findById(2);
        logger.info("loaded2=" + loaded);
        return "ok";
    }

    @RequestMapping("/test1")
    public @ResponseBody String test1() {
        userService.test();
        logger.info("UserInfoController.test1()");
        return "ok";
    }

    @RequestMapping("/delete")
    public @ResponseBody String delete(@RequestParam(defaultValue="1",required=false,name="longid")Integer longid) {
        userService.deleteFromCache(longid);
        return "ok";
    }
    @RequestMapping("/deleteAll")
    public @ResponseBody String deleteAll() {
        userService.deleteAllFromCache();
        return "ok";
    }
    @RequestMapping("/findAll")
    @Cacheable(value="userInfo",keyGenerator="wiselyKeyGenerator")
    public @ResponseBody List<UserInfo> findAll() {
        List<UserInfo> findAll = userInfoService.findAll();
        System.out.println(findAll);
        return findAll;
    }
    @RequestMapping("/findMongodb")
    @Cacheable(value="mongoUser",keyGenerator="wiselyKeyGenerator",condition="")
    public @ResponseBody List<MongoUser> findFromMongodb() {
        List<MongoUser> userList = mongoUserService.getUserList();
        return userList;
    }
    @RequestMapping("/addMongodb")
    public @ResponseBody MongoUser addMongodb() {
        MongoUser user=new MongoUser("jimas", "工程师", 26, "bozhou");
        mongoUserService.addUser(user);
        return user;
    }
}
