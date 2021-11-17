package com.jcpdev.petSitter.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.jcpdev.petSitter.dao.Ps_boardMapper;
import com.jcpdev.petSitter.model.Member;
import com.jcpdev.petSitter.model.Pet;
import com.jcpdev.petSitter.model.Ps_board;
import com.jcpdev.petSitter.model.R_board;
import com.jcpdev.petSitter.model.Reservation;

@Component
public class Ps_boardServiceImpl implements Ps_boardService {
	
	@Autowired
	Ps_boardMapper dao;

	@Override
	public int psb_insert(Ps_board ps_board) {
		List<MultipartFile> files = ps_board.getFiles();
		StringBuilder sb = new StringBuilder();
		String path = "E:\\Program\\upload";

		if (files != null && files.size() > 0) {
			for (MultipartFile f : files) {
				String newpath="";
//				String fileName = "board_" + f.getOriginalFilename();   //원래 파일명
				String fileName = "pet_" + randomString(f.getOriginalFilename());   //원래 파일명
				if (!fileName.equals("")) {
					newpath = path + "\\" + fileName;   //업로드경로+파일명
					sb.append(fileName).append(",");
					// 선택한 파일을 서버로 전송
					File upfile = new File(newpath);
					try {
						f.transferTo(upfile);
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
				}
            }
			ps_board.setG_fname(sb.toString());
         }
		
		System.out.println("게시글 : " + ps_board);
		
		return dao.psb_insert(ps_board);
	}

	public String randomString(String oldfile) {	// 랜덤문자열(숫자10개, 알파벳대소문자 사용)생성
	      int leftLimit = 48; // 숫자 '0'
	      int rightLimit = 122; // 알파벳 'z'
	      int targetStringLength = 10;
	      Random random = new Random();
	      String ext = oldfile.substring(oldfile.indexOf("."), oldfile.length());	// 확장자 추출
	      String rString = random.ints(leftLimit,rightLimit + 1)
	        .filter(i -> (i <= 57 || (i >= 65 && i <= 90) || i >= 97))		// 사용하지 않을 기호 제외
	        .limit(targetStringLength)
	        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	        .toString();
	      
	      //또는 UUID.randomUUID().toString();
	      return rString+ext;	// 랜덤문자열.확장자를 리턴
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
