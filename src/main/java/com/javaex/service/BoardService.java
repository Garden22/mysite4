package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao bDao;
	

	public List<BoardVo> list(String search){		
		List<BoardVo> bList = bDao.list(search);
		
		return bList;
	}
		
	public BoardVo read(int postNo) {
		BoardVo post = bDao.selectPost(postNo);
		
		return post;
	}
	
	public void hit(int postNo) {
		bDao.hit(postNo);
	}
		
	public void write(BoardVo post) {
		bDao.insertPost(post);
	}
	
	public void modify(BoardVo post) {
		bDao.updatePost(post);
	}
	
	public void delete(int no) {
		bDao.deletePost(no);
	}
	
}
