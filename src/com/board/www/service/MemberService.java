package com.board.www.service;

import java.sql.Connection;
import java.util.Scanner;

import com.board.www.dao.MemberDAO;
import com.board.www.dto.MemberDTO;

public class MemberService {
	// 회원에 대한 처리 C(회원가입) R(로그인) U(회원정보수정) D(회원탈퇴)

	public MemberDTO memberMenu(Scanner scanner, MemberDTO loginMember, Connection connection) { // while문으로 부메뉴 반복 처리
		System.out.println("회원관리용 서비스로 진입");
		boolean memberRun = true;
		
		while (memberRun) {
			System.out.println("1.회원가입|2.로그인|3.회원수정|4.회원탈퇴|5.돈주기|6.뒤로가기");
			System.out.print(">>>");
			int memberSelect = scanner.nextInt();
			switch (memberSelect) {
			case 1:
				join(scanner, connection);
				break;
			case 2:
				loginMember = login(scanner, loginMember, connection);
				break;
			case 3:
				modify(scanner, connection);
				break;
			case 4:
				delete(scanner, connection);
				break;
			case 5:
				if(loginMember != null) {
				giveMoney(scanner, connection, loginMember);
				}else {
					System.out.println("로그인 먼저 해주세요.");
				}
				break;
			case 6:
				System.out.println("회원관리메뉴를 종료합니다.");
				memberRun = false;
			}// end switch
		}// end while
		return loginMember;
	}// end memberMenu method

	public void join(Scanner scanner, Connection connection) { // 회원가입용 메서드
		MemberDAO memberDAO = new MemberDAO();
		System.out.println("회원가입 메서드로 진입");
		System.out.print("id : "); // 아이디 중복 체크
		String joinId = scanner.next();
		System.out.print("pw : ");
		String joinPw = scanner.next();
		MemberDTO joinDTO = new MemberDTO(joinId, joinPw);
		memberDAO.register(connection, joinDTO);
		System.out.println("회원가입 완료");
		

	}// end join method

	public MemberDTO login(Scanner scanner, MemberDTO loginMember, Connection connection) { // 회원로그인용 메서드
		MemberDAO memberDAO = new MemberDAO(); // new MemberDAO(connection); 이렇게도 쓸 수 있음
		System.out.println("로그인 메서드로 진입");
		System.out.print("id : ");
		String loginId = scanner.next();
		System.out.print("pw : ");
		String loginPw = scanner.next();
		MemberDTO loginDTO = new MemberDTO(loginId, loginPw); // 세터 안쓰는 방법
		loginMember = memberDAO.login(connection, loginMember, loginDTO);
		return loginMember;
	}// end login method

	public void modify(Scanner scanner, Connection connection) { // 회원정보수정용 메서드
		MemberDAO memberDAO = new MemberDAO(); 
		System.out.println("회원정보수정 메서드로 진입");
		System.out.print("no : ");
		int modifyNo = scanner.nextInt();
		System.out.print("id : ");
		String modifyId = scanner.next();
		System.out.print("pw : ");
		String modifyPw = scanner.next();
		MemberDTO modifyDTO = new MemberDTO(modifyNo, modifyId, modifyPw);
		
		memberDAO.update(connection, modifyDTO);

	}// end modify method

	public void delete(Scanner scanner, Connection connection) { // 회원탈퇴용 메서드
		MemberDAO memberDAO = new MemberDAO();
		System.out.println("회원탈퇴 메서드로 진입");
		System.out.print("id : ");
		String deleteId = scanner.next();
		MemberDTO deleteDTO = new MemberDTO(deleteId);
		
		memberDAO.delete(connection, deleteDTO);
		
	}// end delete method

	public void giveMoney(Scanner scanner, Connection connection, MemberDTO loginMember) {
		MemberDAO memberDAO = new MemberDAO();
		System.out.print("입금 받을 사람의 id : ");
		String giveId = scanner.next();
		System.out.print("이체 금액 : ");
		int giveMoney = scanner.nextInt();
		MemberDTO giveMoneyDTO = new MemberDTO();
		giveMoneyDTO.setMid(giveId);
		giveMoneyDTO.setMmoney(giveMoney);
		
		memberDAO.giveMoney(connection, giveMoneyDTO, loginMember);
		
		
		
		
	}//end giveMoney() method



}
