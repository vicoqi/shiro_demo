package com.vic.university.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vic.university.bean.User;
import com.vic.university.service.user.impl.UserService;

public class TestSpring {
	@Test
	public void testSessionFactory(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean.xml");
//		UserService us = (UserService) context.getBean("userService");
		context.getBean("userController");
//		User user = us.queryUserById(1);
//		System.out.println(user.getUserName());
	}
}
