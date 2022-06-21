package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService bService;
	
	
	// 목록
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("board > list");
		
		List<BoardVo> bList = bService.list("");
		model.addAttribute("bList", bList);
		
		return "/board/list";
	}
	
	@RequestMapping(value="/search", method={RequestMethod.GET, RequestMethod.POST})
	public String search(@RequestParam("search") String search, Model model) {
		System.out.println("board > search");
		
		List<BoardVo> bList = bService.list(search);
		model.addAttribute("bList", bList);
		
		return "/board/list";
	}
	
	
	// 게시글 읽기
	@RequestMapping(value="/read/{postNo}", method={RequestMethod.GET, RequestMethod.POST})
	public String read(@PathVariable("postNo") int postNo, Model model) {
		System.out.println("board > read");
		
		bService.hit(postNo);
		
		BoardVo post = bService.read(postNo);
		model.addAttribute("post", post);
	
		return "/board/read";
	}
	
	
	// 게시글 작성
	@RequestMapping(value="/writeForm", method={RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("board > writeForm");
		
		return "board/writeForm";
	}
	
	@RequestMapping(value="/write", method={RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute("post") BoardVo post) {
		System.out.println("board > write");
		
		bService.write(post);
		
		return "redirect:/board/list";
	}
	
	
	// 게시글 수정
	@RequestMapping(value="/modifyForm", method={RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@RequestParam("no") int no, Model model) {
		System.out.println("board > modifyForm");
		
		BoardVo post = bService.read(no);
		post.setContent(post.getContent().replace("<br>", "\n"));

		model.addAttribute("post", post);
		
		return "board/modifyForm";
	}
	
	@RequestMapping(value="/modify", method={RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute("post") BoardVo post) {
		System.out.println("board > modify");
		
		bService.modify(post);
		
		return "redirect:/board/list";
	}
	
	
	//게시글 삭제
	@RequestMapping(value="/delete/{no}", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable("no") int no) {
		System.out.println("board > delete");
		
		bService.delete(no);
		
		return "redirect:/board/list";
	}
	
}
