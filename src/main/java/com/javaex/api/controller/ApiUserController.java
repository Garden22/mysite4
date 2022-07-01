package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@RequestMapping("/api/user")
@Controller
public class ApiUserController {
	
	@Autowired
	private UserService uService;

	@ResponseBody
	@RequestMapping(value="/idcheck", method={RequestMethod.GET, RequestMethod.POST})
	public boolean idcheck(@RequestBody UserVo test) {
		System.out.println("api user > idcheck");
		
		boolean result = uService.idcheck(test.getId());
		
		return result;
	}
}
