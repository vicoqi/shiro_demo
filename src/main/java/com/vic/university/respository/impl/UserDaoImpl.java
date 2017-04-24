package com.vic.university.respository.impl;

import org.springframework.stereotype.Repository;

import com.vic.university.bean.User;
import com.vic.university.respository.RepositorySupport;

@Repository("userDaoImpl")
public class UserDaoImpl extends RepositorySupport<User>{
	public User findOne(Integer id) {	
		return super.findOne(id);
	}
}
