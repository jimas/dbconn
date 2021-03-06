package com.jimas.dbconn.sourceconfig.mongo;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

@Configuration
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan
@EnableMongoRepositories
public class MongoDataSourceConfig extends AbstractMongoConfiguration{

	@Value("${mongo.database}")
	private String dbname;
	
	@Value("${mongo.host}")
	private String dbhost;
	
	@Value("${mongo.port}")
	private String dbport;
	
	@Value("${mongo.username}")
	private String username;
	
	@Value("${mongo.password}")
	private String password;
	
	@Override
	protected String getDatabaseName() {
		return this.dbname;
	}
	
	public MongoDataSourceConfig(){
		if(null == dbport || "".equalsIgnoreCase(dbport.trim())){
			dbport = "27017";
		}
	}

	@Override
	@Bean(name = "mongods")
	public Mongo mongo() throws Exception {
		ServerAddress serverAdress = new  ServerAddress(dbhost, Integer.valueOf(dbport));        
//		MongoCredential credential = MongoCredential.createMongoCRCredential(username, dbname , password.toCharArray());//XXX linux 机器上可以使用 本地window 机器上 报授权失败
//		MongoCredential credential =MongoCredential.createScramSha1Credential(username, dbname , password.toCharArray());
		MongoCredential credential =MongoCredential.createCredential(username, dbname , password.toCharArray());
		//Do not use new Mongo(), is deprecated.
//	    Mongo mongo =  new MongoClient(serverAdress);//非用户名 密码认证
	    Mongo mongo =  new MongoClient(serverAdress, Arrays.asList(credential));
	    mongo.setWriteConcern(WriteConcern.SAFE);
	    return mongo;
	}
}
