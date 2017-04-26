package com.vic.university.service.user;

import com.vic.university.bean.User;

public interface IUserService {
	User queryUserById(Integer UserId);
	User queryUserByUserName(String userName);
}
