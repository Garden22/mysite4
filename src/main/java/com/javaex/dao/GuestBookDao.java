package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;

@Repository
public class GuestBookDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	// 방명록 조회
	public List<GuestBookVo> selectList() {
		List<GuestBookVo> gbList = sqlSession.selectList("guestbook.selectList");
		
		return gbList;
	}
	
	
	// 방명록 추가
	public int insertVisit(GuestBookVo visit) {
		int count = -1;
		count = sqlSession.insert("guestbook.insertVisit", visit);
		
		return count;
	}
	
	
	// 비밀번호 체크
	public String checkPw(GuestBookVo visit) {
		String result = "fail";
		
		if (sqlSession.selectOne("guestbook.checkPw", visit) != null) result = "success";
		
		return result;
	}
	
	
	// 방명록 삭제
	public int deleteVisit(GuestBookVo visit) {
		int count = -1;
		count = sqlSession.delete("guestbook.deleteVisit", visit);
		
		return count;
	}
}
