package com.jimas.dbconn.service.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jimas.dbconn.dao.mongo.MongoUserRepository;
import com.jimas.dbconn.pojo.mongo.MongoUser;

@Service("mongoUserService")
public class MongoUserService {

	@Autowired
	private MongoUserRepository mongoUserRepository;
	
	public void addUser(MongoUser user){
		mongoUserRepository.addUser(user);
	}
	
	public MongoUser getUser(String username){
		return mongoUserRepository.getUser(username);
	}
}
