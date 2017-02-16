package com.jimas.dbconn.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jimas.dbconn.api.UserInfoApi;
import com.jimas.dbconn.repository.dao.UserInfoMapper;
import com.jimas.dbconn.repository.entity.UserInfo;

@Service
public class UserInfoService implements UserInfoApi{

    private static final Logger logger=Logger.getLogger(UserInfoService.class);
    @Autowired
    private UserInfoMapper mapper;

    @Override
    public UserInfo findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public Boolean create(UserInfo userInfo) {
        int insert = mapper.insert(userInfo);
        return insert>0?true:false;
    }

    @Override
    @Transactional
    public Boolean update(UserInfo userInfo) {
        return mapper.updateByPrimaryKeySelective(userInfo)>0?true:false;
    }

    @Override
    @Transactional
    public Boolean deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id)>0?true:false;
    }

    public List<UserInfo> findAll() {
        logger.info("从数据库中获取列表");
       return  mapper.selectByExample(null);
    }

    
}
