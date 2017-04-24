package com.vic.university.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vic.university.bean.User;
import com.vic.university.respository.impl.UserDaoImpl;
import com.vic.university.service.user.IUserService;
@Service
public class UserService implements IUserService{
	@Autowired
	private UserDaoImpl userDao;

	@Override
	public User queryUserById(Integer userId) {
		// TODO Auto-generated method stub
		User userInfo = userDao.findOne(userId);
		return userInfo;
	}

}
