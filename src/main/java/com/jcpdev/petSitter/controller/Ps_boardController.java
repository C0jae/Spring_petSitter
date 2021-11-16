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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jcpdev.petSitter.service.Ps_boardService;

@Controller
@RequestMapping("/ps_board")
public class Ps_boardController {
	
	private static final Logger logger = LoggerFactory.getLogger(Ps_boardController.class);
	
	@Autowired
	Ps_boardService service;
	
	@RequestMapping(value = "ps_board_write", method = RequestMethod.GET)
	public String ps_boardWrite() {
		return "ps_boardWrite";
	}
}
