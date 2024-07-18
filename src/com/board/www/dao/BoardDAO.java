package com.board.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.board.www.dto.BoardDTO;
import com.board.www.dto.MemberDTO;

public class BoardDAO {
	// 데이터베이스 처리용 C R U D

	public void createBoard(Connection connection, BoardDTO createDTO, MemberDTO loginMember) {
		// connection -> main에서 넘어온 jdbc 1,2 단계
		// loginMemberDTO -> 로그인시 키보드로 입력받은 id, pw 값이 들어 있다.
		// db에 있는 로그인 값을 찾아 옴
		int resultCreate = 0;
		try {
			String sql = "insert into board(bno, btitle, bcontent, bwriter, bdate) values(board_seq.nextval, ?, ?, ?, sysdate)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, createDTO.getBtitle());
			preparedStatement.setString(2, createDTO.getBcontent());
			preparedStatement.setString(3, loginMember.getMid());
			resultCreate = preparedStatement.executeUpdate();

			if (resultCreate > 0) {
				System.out.println(resultCreate + "행의 데이터를 추가했습니다.");
				connection.commit();
			} else {
				System.out.println("결과" + resultCreate + "입니다.");
				System.out.println("입력 실패 : 롤백합니다.");
				connection.rollback();
			}

			preparedStatement.close();

		} catch (SQLException e) {
			System.out.println("관리자 : sql 문을 확인하세요");
			e.printStackTrace();
		}

	}// end createBoard() method

	public void listBoard(Connection connection) {

		// BoardDTO boardDTO = null;

		try {
			String sql = "select bno, btitle, bcontent, bwriter, bdate from board order by bno desc";
			// board 테이블에 있는 데이터를 가져옴
			PreparedStatement preparedStatement = connection.prepareStatement(sql); // 3 단계
			ResultSet resultSet = preparedStatement.executeQuery(); // 4 단계
			// boardDTO = new BoardDTO();
			while (resultSet.next()) { // 표형식으로 리턴된 값 유무 판단
				System.out.print(resultSet.getInt("bno") + "\t");
				System.out.print(resultSet.getString("btitle") + "\t");
				System.out.print(resultSet.getString("bcontent") + "\t");
				System.out.print(resultSet.getString("bwriter") + "\t");
				System.out.println(resultSet.getDate("bdate") + "\t");

//				boardDTO.setBno(resultSet.getInt("bno"));
//				boardDTO.setBtitle(resultSet.getString("btitle"));
//				boardDTO.setBcontent(resultSet.getNString("bcontent"));
//				boardDTO.setBwriter(resultSet.getNString("bwriter"));
//				boardDTO.setBdate(resultSet.getDate("bdate"));

			}
			// 5단계
			resultSet.close();
			preparedStatement.close();

		} catch (SQLException e) {
			System.out.println("BoardDAO.list() sql문 오류");
			e.printStackTrace();
		}

	}// end list() method

	public void updateBoard(Connection connection, BoardDTO updateDTO, MemberDTO loginMember) {
		// connection -> main에서 넘어온 jdbc 1,2 단계
		// loginMemberDTO -> 로그인시 키보드로 입력받은 id, pw 값이 들어 있다.
		// db에 있는 로그인 값을 찾아 옴
		int resultUpdate = 0;
		String compare = null;

		try {
			String sql1 = "select bwriter from board where bno = ?";
			// board 테이블에 있는 데이터를 가져옴
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1); // 3 단계
			preparedStatement1.setInt(1, updateDTO.getBno());
			ResultSet resultSet = preparedStatement1.executeQuery(); // 4 단계
			if(resultSet.next()) { // .next()가 특정 값을 가리키게 됨, 안할시 compare에 값이 들어가지 않음
			compare = resultSet.getString("bwriter");
			}
			// boardDTO = new BoardDTO();
			//resultSet.close();
			preparedStatement1.close();

		} catch (SQLException e) {
			System.out.println("BoardDAO.updateBoard() sql문 오류");
			e.printStackTrace();
		}

		if (compare.equals(loginMember.getMid())) {
			try {
				String sql = "update board set btitle = ?, bcontent = ?, bwriter = ? , bdate = sysdate where bno = ?";

				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, updateDTO.getBtitle());
				preparedStatement.setString(2, updateDTO.getBcontent());
				preparedStatement.setString(3, loginMember.getMid());
				preparedStatement.setInt(4, updateDTO.getBno());
				resultUpdate = preparedStatement.executeUpdate();

				if (resultUpdate > 0) {
					System.out.println(resultUpdate + "행의 데이터를 추가했습니다.");
					connection.commit();
				} else {
					System.out.println("결과" + resultUpdate + "입니다.");
					System.out.println("입력 실패 : 롤백합니다.");
					connection.rollback();
				}

				preparedStatement.close();
				// return loginDTO;

			} catch (SQLException e) {
				System.out.println("관리자 : sql 문을 확인하세요");
				e.printStackTrace();
			}
		} else {
			System.out.println("아이디가 일치하지 않습니다.");
		}
	}// end updateBoard() method

	public void deleteBoard(Connection connection, BoardDTO deleteDTO, MemberDTO loginMember) {
		// connection -> main에서 넘어온 jdbc 1,2 단계
		// loginMemberDTO -> 로그인시 키보드로 입력받은 id, pw 값이 들어 있다.
		// db에 있는 로그인 값을 찾아 옴
		int resultDelete = 0;
		try {
			String sql = "delete from board where bno = ? ";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, deleteDTO.getBno());
			resultDelete = preparedStatement.executeUpdate();

			if (resultDelete > 0) {
				System.out.println(resultDelete + "행의 데이터를 삭제하였습니다.");
				connection.commit();
			} else {
				System.out.println("결과" + resultDelete + "입니다.");
				System.out.println("입력 실패 : 롤백합니다.");
				connection.rollback();
			}

			preparedStatement.close();
			// return loginDTO;

		} catch (SQLException e) {
			System.out.println("관리자 : sql 문을 확인하세요");
			e.printStackTrace();
		}

	}// end deleteBoard() method

}// end class
