package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao uDao;
	
	
	public void join(UserVo user) {
		int count = uDao.insertUser(user);
		
		if (count > 0) System.out.println("[" + count + "건 회원가입 되었습니다.]");
		else System.out.println("[회원가입이 완료되지 않았습니다.]");
	}
	
	
	public UserVo login(UserVo user) {
		UserVo authUser = uDao.getUser(user);
		
		if (authUser != null) {
			System.out.println("[로그인 성공]");
		} else {
			System.out.println("[로그인 실패]");
		}
		
		return authUser;
	}
	
	
	public UserVo userInfo(UserVo authUser) {
		authUser = uDao.userInfo(authUser);
		
		return authUser;
	}
	
	
	public UserVo modifyUser(UserVo user) {
		int count = uDao.userUpdate(user);
		UserVo authUser = null;
		
		if (count > 0) {
			System.out.println("[" + count + "건 업데이트 되었습니다.]");
			
			authUser = uDao.getUser(user);
			
		} else System.out.println("[업데이트 실패]");
				
		return authUser;
	}
	
}
