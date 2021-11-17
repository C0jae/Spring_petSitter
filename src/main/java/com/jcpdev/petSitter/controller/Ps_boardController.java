/*
 	작성자 : 최영재
 	날짜 : 21.11.16
 	기능 : 펫시터 게시판 관련 기능 수행(게시판 만들기, 게시판 상세조회)
 */
package com.jcpdev.petSitter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jcpdev.petSitter.model.Ps_board;
import com.jcpdev.petSitter.service.Ps_boardService;

@Controller
@RequestMapping("/ps_board")
public class Ps_boardController {
	
	private static final Logger logger = LoggerFactory.getLogger(Ps_boardController.class);
	
	@Autowired
	Ps_boardService service;
	
	@RequestMapping(value = "/ps_boardWrite", method = RequestMethod.GET)
	public String ps_boardWrite() {
		logger.info("펫시터 게시글 작성입니다.");
		return "ps_board/ps_boardWrite";
	}
	
	@RequestMapping(value="/ps_boardSave", method=RequestMethod.POST)
	public String ps_boardSave(@ModelAttribute Ps_board ps_board, 
			@RequestParam MultipartFile files) {
		logger.info("펫시터 게시글 저장");
		logger.info("컨트롤러 게시글1 : " + ps_board);
		
		if(!files.isEmpty()) {
			ps_board.setG_fname(files.getOriginalFilename());
		}
//		ps_board.setIdx(idx);
//		ps_board.setTitle(title);
//		ps_board.setContent(content);
//		ps_board.setPs_sdate(ps_sdate);
//		ps_board.setPs_fdate(ps_fdate);
//		ps_board.setP_size(p_size);
//		ps_board.setComment(comment);
//		ps_board.setTerms(terms);
//		ps_board.setM_addr(m_addr);
		
		logger.info("컨트롤러 게시글2 : " + ps_board);
		
		service.psb_insert(ps_board);
		
		return "redirect:../";
	}
}
