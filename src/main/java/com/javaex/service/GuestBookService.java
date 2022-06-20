package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestBookService {

	@Autowired
	private GuestBookDao gbDao;
	
	
	public List<GuestBookVo> getList() {
		List<GuestBookVo> gbList = gbDao.selectList();
		
		return gbList; 
	}
	
	public void addVisit(GuestBookVo visit) {
		gbDao.insertVisit(visit);
	}
	
	public String deleteVisit(GuestBookVo visit) {
		String result = gbDao.deleteVisit(visit);
		
		return result;
	}
	
}
