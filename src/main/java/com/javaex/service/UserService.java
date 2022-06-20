package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	UserDao uDao;
	
	public void join(UserVo user) {
		uDao.insert(user);
	}
	
	public UserVo login(UserVo user) {
		UserVo authUser = null;
		authUser = uDao.getUser(user);
		
		return authUser;
	}
	
	public UserVo userInfo(UserVo authUser) {
		authUser = uDao.userInfo(authUser);
		
		return authUser;
	}
	
	public UserVo modify(UserVo authUser) {
		authUser = uDao.update(authUser);
		
		return authUser;
	}
}
