package com.jimas.dbconn.dao.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.jimas.dbconn.pojo.mongo.MongoUser;

@Repository
public class MongoUserRepository {
	
	@Autowired
    MongoTemplate mongoTemplate;
	
	public MongoUser getUser(String username){
		
		Query query = new Query();
		Criteria criteria = new Criteria();
        criteria = Criteria.where("username").is(username);
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query, MongoUser.class);
	}
	
	public void addUser(MongoUser user){
		mongoTemplate.save(user);
	}
	
	public List<MongoUser> getUserList(){
	    return mongoTemplate.findAll(MongoUser.class);
	}
}
