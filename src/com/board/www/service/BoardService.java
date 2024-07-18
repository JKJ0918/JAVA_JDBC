package com.board.www.service;

import java.sql.Connection;
import java.util.Scanner;

import com.board.www.dao.BoardDAO;
import com.board.www.dto.MemberDTO;
import com.board.www.dto.BoardDTO;

public class BoardService {
	// board 의 부메뉴 (C R U D L)

	public void boardMenu(Scanner scanner, Connection connection, MemberDTO loginMember) {
		boolean boardMenuRun = true;
		while (boardMenuRun) {
			System.out.println("---------------- 게시물 작성 메뉴 ----------------");
			System.out.println("|1.게시글작성|2.게시글목록보기|3.게시글수정|4.게시글삭제|5.종료|");
			System.out.print(">>>");
			int select = scanner.nextInt();
			switch (select) {
			case 1:
				System.out.println("게시글 작성");
				createBoard(scanner, connection, loginMember);
				break;
			case 2:
				System.out.println("게시글 목록 보기");
				listBoard(connection);
				break;
			case 3:
				System.out.println("게시글 수정");
				updateBoard(scanner, connection, loginMember);
				break;
			case 4:
				System.out.println("게시글 삭제");
				deleteBoard(scanner, connection, loginMember);
				break;
			case 5:
				System.out.println("게시글 메뉴 종료");
				boardMenuRun = false;
			default:
				System.out.println("명령어를 입력해 주세요.");
			}// end switch
		} // end while
	}// end boardMenu() method

	public void createBoard(Scanner scanner, Connection connection, MemberDTO loginMember) { // 게시글 작성 (Create)
		System.out.print("글의 제목 : ");
		String btitle = scanner.next();
		System.out.print("내용 입력 : ");
		String bcontent = scanner.next();
		BoardDTO createDTO = new BoardDTO();
		createDTO.setBtitle(btitle);
		createDTO.setBcontent(bcontent);
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.createBoard(connection, createDTO, loginMember);
	}// end createBoard() method
	
	public void listBoard(Connection connection) { // 게시물 목록 보기
		BoardDAO boardDAO = new BoardDAO();
		System.out.println("---------------------");
		System.out.println("==== 대나무숲 게시판 ====");
		System.out.println("[게시물 목록]");
		System.out.println("-------------------------------------------------");
		System.out.println("no          title          writer          date  ");
		System.out.println("-------------------------------------------------");
		boardDAO.listBoard(connection);
	}// end list() method

	public void updateBoard(Scanner scanner, Connection connection, MemberDTO loginMember) {
		BoardDAO boardDAO = new BoardDAO();
		System.out.print("글 번호 : ");
		int updateBno = scanner.nextInt();
		System.out.println("수정 글 제목 : ");
		String updateBtitle = scanner.next();
		System.out.println("수정 글 내용 : ");
		String updateBcontent = scanner.next();
		BoardDTO updateDTO = new BoardDTO();
		updateDTO.setBno(updateBno);
		updateDTO.setBtitle(updateBtitle);
		updateDTO.setBcontent(updateBcontent);
		boardDAO.updateBoard(connection, updateDTO, loginMember );
		
	}//end updateBoard() method

	public void deleteBoard(Scanner scanner, Connection connection, MemberDTO loginMember) {
		BoardDAO boardDAO = new BoardDAO();
		System.out.print("글 번호 : ");
		int deleteBno = scanner.nextInt();
		BoardDTO deleteDTO = new BoardDTO();
		deleteDTO.setBno(deleteBno);
		boardDAO.deleteBoard(connection, deleteDTO, loginMember);
		
	}


}// end class
