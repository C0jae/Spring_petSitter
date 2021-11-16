package com.jcpdev.petSitter.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jcpdev.petSitter.dao.Ps_boardMapper;
import com.jcpdev.petSitter.model.Member;
import com.jcpdev.petSitter.model.Pet;
import com.jcpdev.petSitter.model.Ps_board;
import com.jcpdev.petSitter.model.R_board;
import com.jcpdev.petSitter.model.Reservation;

@Service
public class Ps_boardServiceImpl implements Ps_boardService {
	
	private final Ps_boardMapper dao;
	
	public Ps_boardServiceImpl(Ps_boardMapper dao) { // 생성자 자동주입 @Autowired생략
		this.dao = dao;
	}

	@Override
	public int psb_insert(Ps_board ps_board) {
		return 0;
	}

	@Override
	public Ps_board psb_getList(int psb_idx) {
		return null;
	}

	@Override
	public List<Pet> p_getList(int idx) {
		return null;
	}

	@Override
	public Member m_getList(int idx) {
		return null;
	}

	@Override
	public double rate(String ps_nick) {
		return 0;
	}

	@Override
	public String rateCnt(String ps_nick) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int psr_insert(Reservation reservation) {
		return 0;
	}

	@Override
	public int plusPoint(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int minusPoint(Map<String, Object> map) {
		return 0;
	}

	@Override
	public int plusIncome(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<R_board> r_getList(String ps_nick) {
		return null;
	}

	@Override
	public int psb_update(Ps_board ps_board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int psb_delete(int psb_idx) {
		return 0;
	}

	@Override
	public String checkIncome() {
		return null;
	}


}
