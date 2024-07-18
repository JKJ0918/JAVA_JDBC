package com.board.www;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.board.www.dao.BoardDAO;
import com.board.www.dto.MemberDTO;
import com.board.www.service.BoardService;
import com.board.www.service.MemberService;

public class BoardMain {
	// 필드
	public static Scanner scanner = new Scanner(System.in);
	// public static BoardDAO boardDAO = new BoardDAO(); // jdbc
	public static Connection connection = null;
	public static MemberDTO loginMember = null; // 로그인 후 객체
	// 생성자

	public BoardMain() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 1 단계 (드라이버명)
			connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.111.103:1521:orcl", "boardtest",
					"boardtest"); // 2단계 (url, id, pw)

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 명 또는 ojdbc6.jar를 확인해주세요.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("url, id, pw나 쿼리문이 잘못됨");
			e.printStackTrace();
			System.exit(0); // 프로그램 강제종료
		}

	} // end BoardMain() method

	// 메서드

	public static void main(String[] args) {
		// 기본적인 설정 : 스케너, jdbc 연동, 주메뉴

		BoardMain boardMain = new BoardMain(); // 생성자 호출 -> 1단계, 2단계 실행(*키자마자 생성자가 driver연결)
		boolean run = true;
		System.out.println(" MBC 아카데미 대나무숲 오신걸 환영합니다.");

		while (run) {
			System.out.println("1.회원 | 2.게시판 | 3.종료");
			System.out.print(">>>");
			int select = scanner.nextInt();
			switch (select) {
			case 1:
				System.out.println("회원용 서비스로 진입합니다.");
				MemberService memberService = new MemberService(); // 이렇게 부르면 누를때만 객체 불러와 져서 메모리 관리에 도움이 될 수 잇음
				loginMember = memberService.memberMenu(scanner, loginMember, connection);
				if (loginMember != null) {
					System.out.println(loginMember.getMid() + "님 환영합니다.");
					// 회원 서비스에서 나올때 로그인 정보가 유지 돼야함
				}
				break;
			case 2:
				if(loginMember != null) {
				System.out.println("게시판 서비스로 진입합니다.");
				BoardService boardService = new BoardService();
				boardService.boardMenu(scanner, connection, loginMember);
				}else {
					System.out.println("로그인 부터 진행해 주세요.");
				}
				break;
			case 3:
				System.out.println("대나무 숲을 종료합니다.");
				run = false;
				break;

			}// switch 문 종료

		} // while 문 종료

	}//end main

}//end class
