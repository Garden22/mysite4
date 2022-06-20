package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public List<BoardVo> list(String find) {
		if (find == "") System.out.println("[글 목록을 불러옵니다.]");
		else System.out.println("[" + find + "를 검색합니다.]");
		
		find = '%' + find + '%';
		List<BoardVo> bList = sqlSession.selectList("board.list", find);
		
		System.out.println("[" + bList.size() + "건 검색되었습니다.]");
		
		return bList;
	}
	
	
	public BoardVo selectPost(int postNo) {
		BoardVo post = sqlSession.selectOne("board.selectPost", postNo);
				
		return post;
	}
	
	
	public void hit(int postNo) {
		int count = -1;
		
		count = sqlSession.update("board.hit", postNo);
		
		if (count > 0) System.out.println("[" + postNo + "번 글 조회수++]");
	}
	
	
	public void insertPost(BoardVo post) {
		int count = -1;
		System.out.println(post.getContent());

		post.setContent(post.getContent().replace("\n", "<br>"));
		System.out.println(post.getContent());

		count = sqlSession.insert("board.insertPost", post);
		
		if (count > 0) System.out.println("[" + count + "건 등록되었습니다.]");
		else System.out.println("[게시글이 등록되지 않았습니다.]");
	}
	
	
	public void updatePost(BoardVo post) {
		int count = -1;
		post.setContent(post.getContent().replace("\n", "<br>"));
		count = sqlSession.update("board.updatePost", post);
		
		if (count > 0) System.out.println("[" + count + "건 수정되었습니다.]");
		else System.out.println("[게시글이 수정되지 않았습니다.]");
	}
	
	
	public void deletePost(int no) {
		int count = -1;
		
		count = sqlSession.delete("board.deletePost", no);
		
		if (count > 0) System.out.println("[" + count + "건 삭제되었습니다.]");
		else System.out.println("[게시글이 삭제되지 않았습니다.]");
	}

}
