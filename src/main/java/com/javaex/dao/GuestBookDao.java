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
	public void insertVisit(GuestBookVo visit) {
		int count = -1;
		count = sqlSession.insert("guestbook.insertVisit", visit);
		
		if (count > 0) System.out.println("[" + count + "건 등록되었습니다.]");
		else System.out.println("[방명록이 등록되지 않았습니다.]");
	}
	
	
	// 방명록 삭제
	public String deleteVisit(GuestBookVo visit) {
		int count = -1;
		String result = "fail";
		
		if (sqlSession.selectOne("guestbook.findPw", visit) != null) {
			count = sqlSession.delete("guestbook.deleteVisit", visit);
		}
		
		if (count > 0) {
			System.out.println("[" + count + "건 삭제되었습니다.]");
			
			result = "success";
		} else System.out.println("[비밀번호를 확인하세요.]");
		
		return result;
	}
}
