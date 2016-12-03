package com.jimas.dbconn.service.mysql;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jimas.dbconn.cache.redis.service.RedisService;
import com.jimas.dbconn.dao.mysql.IMysqlUser;
import com.jimas.dbconn.pojo.mysql.MysqlUser;

@Service("mysqlUserService")
public class MysqlUserService {
    private static final Logger logger=Logger.getLogger(MysqlUserService.class);
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
        logger.info("MysqlUserService.findById()=========从数据库中进行获取的....id="+userId);
        return  mysqlUser.getUser(userId);
    }

    public void test() {
        redisService.set("mykey6","random1="+Math.random());
        logger.info(redisService.get("mykey6"));
    }

    @CacheEvict(value="userInfo",key="'userInfo_'+#userId")
    public void deleteFromCache(Integer userId) {
        logger.info("MysqlUserService.delete("+userId+").从缓存中删除.");
    }
    @CacheEvict(value="userInfo",allEntries=true,beforeInvocation=true) 
    public void deleteAllFromCache() {
        logger.info("MysqlUserService.deleteAll().从缓存中删除.");
    }
}
