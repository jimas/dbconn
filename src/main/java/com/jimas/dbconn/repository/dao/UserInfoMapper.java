package com.jimas.dbconn.repository.dao;

import com.jimas.dbconn.repository.entity.UserInfo;
import com.jimas.dbconn.repository.entity.UserInfoCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    int countByExample(UserInfoCriteria example);

    int deleteByExample(UserInfoCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    List<UserInfo> selectByExample(UserInfoCriteria example);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoCriteria example);

    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoCriteria example);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}