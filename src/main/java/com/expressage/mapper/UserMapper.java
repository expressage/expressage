package com.expressage.mapper;

import org.apache.ibatis.annotations.Param;

import com.expressage.pojo.User;

public interface UserMapper {
	int zm_addUser(User user);

	int zm_selUserByTel(@Param("tel")String tel);

	int zm_updUser(User user);
	
	User zm_login(@Param("tel")String tel);
	
	

	int deleteByPrimaryKey(Integer uid);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer uid);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);
}