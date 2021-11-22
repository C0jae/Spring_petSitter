/*
 	작성자 : 최영재
 	날짜 : 21.11.16
 	기능 : 펫시터 게시판 관련 기능 수행(게시판 만들기, 게시판 상세조회)
 */
package com.jcpdev.petSitter.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jcpdev.petSitter.model.Member;
import com.jcpdev.petSitter.model.Pet;
import com.jcpdev.petSitter.model.Ps_board;
import com.jcpdev.petSitter.model.R_board;
import com.jcpdev.petSitter.model.Reservation;
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
		
		return "alert";
	}
	
	// 게시글 불러오기
	@RequestMapping(value="/ps_boardRead", method=RequestMethod.GET)
	public String ps_boardRead(/* @RequestParam int psb_idx, int idx, */ Model model) {
		
		// 게시글idx, 이용회원 idx, 시작일, 종료일 파라미터 받아서 넘겨주기
		
		int psb_idx = 18;
		int idx = 5;		// 이용회원번호
		Member petSitter = service.ps_getList(psb_idx);			// 펫시터 회원정보
		Member user = service.m_getList(idx);					// 이용자 회원정보
		Ps_board ps_board = service.psb_getList(psb_idx);		// 게시글 정보
		List<Pet> pet = service.p_getList(petSitter.getIdx());	// 펫시터 펫정보
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
	
	// 예약 및 결제
	@RequestMapping(value="/ps_reserve", method=RequestMethod.POST)
	public String ps_reserve(@RequestParam int idx, int ps_idx, int psb_idx, 
			String s_date, String f_date, String small, String middle, String big, 
			Model model) {
		
		// 게시글 넘어갈때 idx, psb_idx, s_date, f_date 넘겨주기
		
		Reservation reservation = new Reservation();
		
		Member user = service.m_getList(idx);			// 이용자 회원정보
		Member petSitter = service.m_getList(ps_idx);	// 펫시터 회원정보
		
		int userPoint = user.getPoint();		// 이용자 보유 포인트
		int psPoint = petSitter.getPoint();		// 펫시터 보유 포인트
		
		// 날짜 및 포인트 확인 결과 불러오기
		int money = service.calculate(s_date, f_date, small, middle, big, userPoint);
		
		// 반려견 수 미입력 or 자연수가 아닌 수 입력
		if (money == -1) {
			String message ="맡기시는 반려견의 수를 정확히 입력해주세요.";
			String url = "ps_boardRead";
			
			model.addAttribute("message", message);
			model.addAttribute("url", url);
			
			return "alert";
		}
		// 시작일 > 종료일
		else if (money == -2) {
			String message ="종료일이 시작일보다 빠릅니다. 다시 입력해주세요.";
			String url = "ps_boardRead";
			
			model.addAttribute("message", message);
			model.addAttribute("url", url);
			
			return "alert";
		}
		// 이용자의 결제 포인트 부족
		else if (money == -3) {
			String message ="포인트가 부족합니다. 보유 포인트를 확인해 주세요.";
			String url = "ps_boardRead";
			
			model.addAttribute("message", message);
			model.addAttribute("url", url);
			
			return "alert";
		}
		// 거래조건 충족(날짜, 포인트 체크 완료)
		else {
			int vat = money / 10;		// 수수료(수익)
			int pay = money + vat;		// 결제금액
			
			// 펫시터 포잍트 증가
			Map<String, Object> plusPoint = new HashMap<>();
			plusPoint.put("money", money);
			plusPoint.put("idx", ps_idx);
			service.plusPoint(plusPoint);
			
			// 예약자 포인트 감소
			Map<String, Object> minusPoint = new HashMap<>();
			minusPoint.put("pay", pay);
			minusPoint.put("idx", idx);
			service.minusPoint(minusPoint);
			
			// 수익증가
			Map<String, Object> plusIncome = new HashMap<>();
			plusIncome.put("vat", vat);
			plusIncome.put("idx", idx);
			service.plusIncome(plusIncome);
			
			// 예약 테이블 insert
			Date s_date2 = Date.valueOf(s_date);
			Date f_date2 = Date.valueOf(f_date);
			
			reservation.setIdx(idx);
			reservation.setPs_idx(ps_idx);
			reservation.setPay(pay);
			reservation.setS_date(s_date2);
			reservation.setF_date(f_date2);
			service.psr_insert(reservation);
			
			String message = "결제가 완료되었습니다.";
			String message2 = "펫정보를 입력해주세요.";
			String url = "home";
			
			model.addAttribute("message", message);
			model.addAttribute("message2", message2);
			model.addAttribute("url", url);
			
			return "alert";
		}
	}
	
	@RequestMapping(value="/psb_update", method=RequestMethod.GET)
	public String psb_update(@RequestParam int psb_idx, String nick, Model model) {
		Ps_board ps_board = service.psb_getList(psb_idx);
		
		model.addAttribute("ps_board", ps_board);
		model.addAttribute("nick", nick);
		
		return "ps_board/ps_boardUpdate";
	}
	
	@RequestMapping(value="/psb_updateSave", method=RequestMethod.POST)
	public String psb_updateSave(@ModelAttribute Ps_board ps_board, 
			@RequestParam MultipartFile files, Model model) {
		if(!files.isEmpty()) {
			ps_board.setG_fname(files.getOriginalFilename());
		}
		
		service.psb_update(ps_board);
		
		String message = "수정이 완료되었습니다.";
		model.addAttribute("message", message);
		model.addAttribute("url", "ps_boardRead");
		model.addAttribute("psb_idx", ps_board.getPsb_idx());
		
		return "alert";
	}
	
	@RequestMapping(value="/psb_delete", method=RequestMethod.GET)
	public String psb_delete(@RequestParam int psb_idx, Model model) {
		service.psb_delete(psb_idx);
		
		String message = "삭제가 완료되었습니다.";
		model.addAttribute("message", message);
		model.addAttribute("url", "home");
		
		return "alert";
	}
	
}
