package com.jcpdev.petSitter.dao;

import com.jcpdev.petSitter.model.Ps_board;

public interface Ps_boardMapper {
	int psb_insert(Ps_board ps_board);
	Ps_board psb_getList(int psb_idx);
	
	
	
//	List<Customer> selectAll();
//	Ps_boardMapper selectOne(int idx);
//	int insert(Customer customer);
//	int update(Customer customer);
//	int delete(int idx);
//	Customer login(Customer customer);
}
