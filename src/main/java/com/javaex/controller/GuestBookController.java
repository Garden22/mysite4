package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	
	@Autowired
	private GuestBookService gbService;
	
	
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String getList(Model model) {
		System.out.println("guestbook > list");
		
		List<GuestBookVo> gbList = gbService.getList();
		model.addAttribute("gbList", gbList);
		
		return "guestbook/addList";
	}

}
