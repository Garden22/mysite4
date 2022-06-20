package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired  
	private SqlSession sqlSession;
	
	
	// 회원가입
	public void insertUser(UserVo user) {
		int count = -1;
		count = sqlSession.insert("user.insertUser", user);
		
		if (count > 0) System.out.println("[" + count + "건 회원가입 되었습니다.]");
		else System.out.println("[회원가입이 완료되지 않았습니다.]");
	}
	
	
	// id & pw 확인 > 세션용 authUser 불러오기
	public UserVo getUser(UserVo user) {
		UserVo authUser = null;
		authUser = sqlSession.selectOne("user.getUser", user);
		
		if (authUser != null) {
			System.out.println("[로그인 성공]");
		} else {
			System.out.println("[로그인 실패]");
		}
		
		return authUser;
	}
	
	
	// 정보 다 있는 authUser 불러오기(수정용)
	public UserVo userInfo(UserVo authUser) {
		authUser = sqlSession.selectOne("user.userInfo", authUser);
		
		return authUser;
	}
	
	
	// 회원 정보 업데이트
	public UserVo userUpdate(UserVo authUser) {
		int count = -1;
		count = sqlSession.update("user.userUpdate", authUser);
		
		if (count < 1) {
			System.out.println("[업데이트 실패]");
		} else {
			System.out.println("[" + count + "건 업데이트 되었습니다.]");
			
			authUser = sqlSession.selectOne("user.getUser", authUser);
		}
	
		return authUser;
	}

}
