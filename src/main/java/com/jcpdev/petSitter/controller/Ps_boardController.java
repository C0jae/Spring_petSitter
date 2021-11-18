/*
 	작성자 : 최영재
 	날짜 : 21.11.16
 	기능 : 펫시터 게시판 관련 기능 수행(게시판 만들기, 게시판 상세조회)
 */
package com.jcpdev.petSitter.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.jcpdev.petSitter.model.Member;
import com.jcpdev.petSitter.model.Pet;
import com.jcpdev.petSitter.model.Ps_board;
import com.jcpdev.petSitter.model.R_board;
import com.jcpdev.petSitter.service.Ps_boardService;

@Controller
//@SessionAttributes("user")
public class Ps_boardController {
	
	private static final Logger logger = LoggerFactory.getLogger(Ps_boardController.class);
	
	@Autowired
	Ps_boardService service;
	
	// 게시글 작성화면 이동
	@RequestMapping(value = "/ps_boardWrite", method = RequestMethod.GET)
	public String ps_boardWrite(/* Model model, HttpSession session */) {
		logger.info("펫시터 게시글 작성입니다.");
//		model.addAttribute("user", session);
		return "ps_board/ps_boardWrite";
	}
	
	// 게시글 저장
	@RequestMapping(value="/ps_boardSave", method=RequestMethod.POST)
	public String ps_boardSave(@ModelAttribute Ps_board ps_board, 
			@RequestParam MultipartFile files, Model model) {
		logger.info("펫시터 게시글 저장");
		
		if(!files.isEmpty()) {
			ps_board.setG_fname(files.getOriginalFilename());
		}
		
		logger.info("게시글저장 컨트롤러 : " + ps_board);
		
		service.psb_insert(ps_board);
		
		String message = "작성이 완료되었습니다.";
		String message2 = "펫정보 입력화면으로 이동합니다.";
		model.addAttribute("message", message);
		model.addAttribute("message2", message2);
		model.addAttribute("url", "home");
		
//		return "redirect:../";
		return "alert";
	}
	
	// 게시글 불러오기
	@RequestMapping(value="/ps_boardRead", method=RequestMethod.GET)
	public String ps_boardRead(/* @RequestParam int psb_idx, int idx, */ Model model) {
		int psb_idx = 18;
		int idx = 4;		// 이용회원번호
		Member petSitter = service.ps_getList(psb_idx);			// 펫시터 회원정보
		Member user = service.m_getList(idx);					// 이용자 회원정보
		Ps_board ps_board = service.psb_getList(psb_idx);		// 게시글 정보
		List<Pet> pet = service.p_getList(idx);					// 펫시터 펫정보
		double rate = service.rate(petSitter.getNick());		// 펫시터 평정
		String rateCnt = service.rateCnt(petSitter.getNick());	// 펫시터 평점 갯수
		List<R_board> review = service.r_getList(petSitter.getNick());	// 펫시터 후기목록
		
		String p_size = ps_board.getP_size();				// 허용가능 펫사이즈 목록 불러오기
		
		model.addAttribute("petSitter", petSitter);
		model.addAttribute("user", user);
		model.addAttribute("ps_board", ps_board);
		model.addAttribute("pet", pet);
		model.addAttribute("rate", rate);
		model.addAttribute("rateCnt", rateCnt);
		model.addAttribute("review", review);
		model.addAttribute("p_size", p_size);
		
		return "ps_board/ps_boardRead";
	}
	
}
