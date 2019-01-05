package com.expressage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expressage.mapper.UserMapper;
import com.expressage.pojo.User;
import com.expressage.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	public int zm_addUser(User user) {
		return userMapper.zm_addUser(user);
	}

	public int zm_selUserByTel(String tel) {
		return userMapper.zm_selUserByTel(tel);
	}

	public int zm_updUser(User user) {
		return userMapper.zm_updUser(user);
	}

	public User zm_login(String tel) {
		return userMapper.zm_login(tel);
	}

}
