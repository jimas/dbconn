package com.jimas.dbconn.api;

import com.jimas.dbconn.repository.entity.UserInfo;

public interface UserInfoApi {
    
    public UserInfo findById(Integer id);
    
    public Boolean  create(UserInfo userInfo);
    
    public Boolean update(UserInfo userInfo);
    
    public Boolean deleteById(Integer id);

}
