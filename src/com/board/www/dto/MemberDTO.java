package com.board.www.dto;

import java.sql.Date;

public class MemberDTO {
	//필드
	private int mno;
	private String mid;
	private String mpw;
	private int mmoney;
	private Date mdate;
	
	//생성자
	public MemberDTO() {} 
	// 기본생성자 -> new MemberDTO();

	public MemberDTO(int modifyNo, String modifyId, String modifyPw) {
		this.mno = modifyNo;
		this.mid = modifyId;
		this.mpw = modifyPw;
	} // 커스텀 생성자 -> id와 pw 처리용
	
	
	
	public MemberDTO(String loginId, String loginPw) {
		this.mid = loginId;
		this.mpw = loginPw;
	} // 커스텀 생성자 -> id와 pw 처리용
	
	
	public MemberDTO(String deleteId) {
		this.mid = deleteId;
	} // 커스텀 생성자 -> id 처리용
	
	





	//메서드 | 게터/세터
	public int getMno() {
		return mno;
	}

	public String getMid() {
		return mid;
	}

	public String getMpw() {
		return mpw;
	}
	
	public int getMmoney() {
		return mmoney;
	}
	
	public Date getMdate() {
		return mdate;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	
	public void setMmoney(int mmoney) {
		this.mmoney = mmoney;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}
	
	
	//테스트중
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	
}
