package com.jimas.dbconn.dao.mysql;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.jimas.dbconn.pojo.mysql.MysqlUser;

public interface IMysqlUser {

	@Select("SELECT * FROM user WHERE id = #{userId}")
	public MysqlUser getUser(int userId);

    @Insert("insert into user (username, job, age, hometown) values(#{username}, #{job}, #{age}, #{hometown})")
    public int addUser(MysqlUser user);
}
