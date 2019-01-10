package com.expressage.mapper;

import org.apache.ibatis.annotations.Param;

import com.expressage.pojo.User;

public interface UserMapper {
	int zm_addUser(User user);

	int zm_selUserByTel(@Param("tel")String tel);

	int zm_updUser(User user);
	
	User zm_login(@Param("tel")String tel);
	
	User zm_selUserByUid(@Param("uid")Integer uid);
}