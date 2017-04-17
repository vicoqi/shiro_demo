package com.vic.university.service.user.impl;

import org.springframework.stereotype.Service;

import com.vic.university.bean.User;
import com.vic.university.service.user.IUserService;
@Service
public class UserService implements IUserService{

	@Override
	public User queryUserById(Integer userId) {
		// TODO Auto-generated method stub
		User userInfo = new User();
		userInfo.setUserId(userId);
		userInfo.setUserName("lisan");
		userInfo.setAge(20);
		return userInfo;
	}

}
