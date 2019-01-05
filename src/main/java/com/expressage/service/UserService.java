package com.expressage.service;

import com.expressage.pojo.User;

public interface UserService {
	int zm_addUser(User user);

	int zm_selUserByTel(String tel);

	int zm_updUser(User user);

	User zm_login(String tel);
}
