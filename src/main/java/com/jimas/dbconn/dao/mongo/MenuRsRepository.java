package com.jimas.dbconn.dao.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.jimas.dbconn.pojo.rest.MenuPojo;
@Repository
public class MenuRsRepository {
    
    @Autowired
    MongoTemplate mongoTemplate;

    public void saveMenu(MenuPojo rs) {
        mongoTemplate.save(rs);
    }

    public MenuPojo getMenuRsBySiteSource(String siteSource) {
        Query query=new Query();
        CriteriaDefinition criteriaDefinition= Criteria.where("siteSource").is(siteSource);
        query.addCriteria(criteriaDefinition);
        return  mongoTemplate.findOne(query, MenuPojo.class);
    }
    
    
    
}
