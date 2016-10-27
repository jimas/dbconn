package com.shihuc.dbconn.service.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.shihuc.dbconn.cache.redis.service.RedisService;
import com.shihuc.dbconn.dao.mysql.IMysqlUser;
import com.shihuc.dbconn.pojo.mysql.MysqlUser;

@Service("mysqlUserService")
public class MysqlUserService {
	
	@Autowired
	private IMysqlUser mysqlUser; 
	@Autowired
	private RedisService redisService; 
	
	public int addUser(MysqlUser userToAdd) {
		return mysqlUser.addUser(userToAdd);
	}

    public MysqlUser getUser(int userId) {
        return mysqlUser.getUser(userId);
    }

//    @Cacheable(value="userInfo",keyGenerator = "wiselyKeyGenerator")
    @Cacheable(value="userInfo",key="'userInfo_'+#userId")
    public MysqlUser findById(int userId) {
        System.err.println("MysqlUserService.findById()=========从数据库中进行获取的....id="+userId);
        return  mysqlUser.getUser(userId);
    }

    public void test() {
        redisService.set("mykey6","random1="+Math.random());
        System.out.println(redisService.get("mykey6"));
    }

    @CacheEvict(value="userInfo",key="'userInfo_'+#userId")
    public void deleteFromCache(Integer userId) {
        System.out.println("MysqlUserService.delete("+userId+").从缓存中删除.");
    }
    @CacheEvict(value="userInfo",allEntries=true,beforeInvocation=true) 
    public void deleteAllFromCache() {
        System.out.println("MysqlUserService.deleteAll().从缓存中删除.");
//        throw new RuntimeException();// beforeInvocation=true 可以确保缓存被清空
    }
}
