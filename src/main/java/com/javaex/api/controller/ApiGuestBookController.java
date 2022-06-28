package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@RequestMapping("/api/guestbook")
@Controller
public class ApiGuestBookController {
	
	@Autowired
	private GuestBookService gbService;
	

	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList() {
		System.out.println("api.guestbook > list");
		
		return "apiGuestbook/addList";
	}
	
	
	@ResponseBody
	@RequestMapping(value="/show", method= {RequestMethod.GET, RequestMethod.POST})
	public List<GuestBookVo> show() {
		System.out.println("api.guestbook > show");
		
		List<GuestBookVo> gbList = gbService.getList();
		
		return gbList;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/add", method= {RequestMethod.GET, RequestMethod.POST})
	public GuestBookVo add(GuestBookVo gbVo) {
		System.out.println("api.guestbook > add");
		
		GuestBookVo visit = gbService.add(gbVo);

		return visit;
	}
}
