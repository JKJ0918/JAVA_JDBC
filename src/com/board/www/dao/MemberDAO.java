package com.board.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.board.www.dto.MemberDTO;

public class MemberDAO {
	// 회원 db에 대한 C(회원가입) R(로그인) U(회원정보수정) D(회원탕퇴)

	public MemberDAO() {
	}// 기본 생성자

	public MemberDAO(Connection connection) {
		// TODO Auto-generated constructor stub
	}// 커스텀 생성자

	public void register(Connection connection, MemberDTO joinDTO) { // 회원가입 처리
		// connection -> main에서 넘어온 jdbc 1,2 단계
		// loginMemberDTO -> 로그인시 키보드로 입력받은 id, pw 값이 들어 있다.
		// db에 있는 로그인 값을 찾아 옴
		int resultRegister = 0;
		try {
			String sql = "insert into member(mno, mid, mpw, mdate) values(board_seq.nextval, ?, ?, sysdate)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, joinDTO.getMid()); // 널?
			// service에서 받은 id가 첫번째 ? 에 적용
			preparedStatement.setString(2, joinDTO.getMpw());
			// service에서 받은 pw가 두번째 ? 에 적용

			resultRegister = preparedStatement.executeUpdate();

			if (resultRegister > 0) {
				System.out.println(resultRegister + "행의 데이터를 추가했습니다.");
				connection.commit();
			} else {
				System.out.println("결과" + resultRegister + "입니다.");
				System.out.println("입력 실패 : 롤백합니다.");
				connection.rollback();
			}

			preparedStatement.close();
			// return loginDTO;

		} catch (SQLException e) {
			System.out.println("찾는 id와 pw가 없습니다.");
			System.out.println("관리자 : sql 문을 확인하세요");
			System.out.println("회원은 id 와 pw를 확인하세요.");
			e.printStackTrace();
		}

	}// end register method

	public MemberDTO login(Connection connection, MemberDTO loginMember, MemberDTO loginDTO) { // 로그인 처리
		// connection -> main에서 넘어온 jdbc 1,2 단계
		// loginMemberDTO -> 로그인시 키보드로 입력받은 id, pw 값이 들어 있다.
		// db에 있는 로그인 값을 찾아 옴
		MemberDTO loginInfo = new MemberDTO();
		try {
			String sql = "select mno, mid, mpw, mmoney, mdate from member where mid = ? and mpw = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginDTO.getMid()); // 널?
			// service에서 받은 id가 첫번째 ? 에 적용
			preparedStatement.setString(2, loginDTO.getMpw());
			// service에서 받은 pw가 두번째 ? 에 적용

			ResultSet resultSet = preparedStatement.executeQuery();
			// 위에서 만든 쿼리 문을 실행하고 결과를 resultSet 표로 받는다.

			while (resultSet.next()) {
				loginInfo.setMno(resultSet.getInt("mno"));
				loginInfo.setMid(resultSet.getString("mid"));
				loginInfo.setMpw(resultSet.getString("mpw"));
				loginInfo.setMdate(resultSet.getDate("mdate"));
				// resultSet 표에 있는 정보를 MemberDTO 객체에 넣음
			} // end while
			loginMember = loginInfo;
			resultSet.close();
			preparedStatement.close();

			// return loginDTO;

		} catch (SQLException e) {
			System.out.println("찾는 id와 pw가 없습니다.");
			System.out.println("관리자 : sql 문을 확인하세요");
			System.out.println("회원은 id 와 pw를 확인하세요.");
			e.printStackTrace();
		}
		return loginMember;
	}// end login method

	public void update(Connection connection, MemberDTO modifyDTO) { // 회원 정보 수정
		// connection -> main에서 넘어온 jdbc 1,2 단계
		// loginMemberDTO -> 로그인시 키보드로 입력받은 id, pw 값이 들어 있다.
		// db에 있는 로그인 값을 찾아 옴
		int resultUpdate = 0;
		try {
			String sql = "update member set mid = ?, mpw = ?, mdate = sysdate where mno = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, modifyDTO.getMid()); // 널?
			// service에서 받은 id가 첫번째 ? 에 적용
			preparedStatement.setString(2, modifyDTO.getMpw());
			// service에서 받은 pw가 두번째 ? 에 적용
			preparedStatement.setInt(3, modifyDTO.getMno());
			// service에서 받은 pw가 두번째 ? 에 적용
			
			resultUpdate = preparedStatement.executeUpdate();

			if (resultUpdate > 0) {
				System.out.println(resultUpdate + "행의 데이터를 변경했습니다.");
				connection.commit();
			} else {
				System.out.println("결과" + resultUpdate + "입니다.");
				System.out.println("회원정보수정 실패 : 롤백합니다.");
				connection.rollback();
			}

			preparedStatement.close();
			// return loginDTO;

		} catch (SQLException e) {
			System.out.println("찾는 id와 pw가 없습니다.");
			System.out.println("관리자 : sql 문을 확인하세요");
			System.out.println("회원은 id 와 pw를 확인하세요.");
			e.printStackTrace();
		}
	}// end update method

	public void delete(Connection connection, MemberDTO deleteDTO) { // 회원 탈퇴
		// connection -> main에서 넘어온 jdbc 1,2 단계
		// loginMemberDTO -> 로그인시 키보드로 입력받은 id, pw 값이 들어 있다.
		// db에 있는 로그인 값을 찾아 옴
		int resultDelete = 0;
		try {
			String sql = "delete from member where mid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, deleteDTO.getMid()); // 널?
			// service에서 받은 pw가 두번째 ? 에 적용

			resultDelete = preparedStatement.executeUpdate();

			if (resultDelete > 0) {
				System.out.println(resultDelete + "행의 데이터를 삭제했습니다.");
				connection.commit();
			} else {
				System.out.println("결과" + resultDelete + "입니다.");
				System.out.println("삭제 실패 찾는 값이 없습니다.: 롤백합니다.");
				connection.rollback();
			}

			preparedStatement.close();
			// return loginDTO;

		} catch (SQLException e) {
			System.out.println("찾는 id와 pw가 없습니다.");
			System.out.println("관리자 : sql 문을 확인하세요");
			System.out.println("회원은 id 와 pw를 확인하세요.");
			e.printStackTrace();
		}
	}// end delete method

	public void giveMoney(Connection connection, MemberDTO giveMoneyDTO, MemberDTO loginMember) {
		// connection -> main에서 넘어온 jdbc 1,2 단계
		// loginMemberDTO -> 로그인시 키보드로 입력받은 id, pw 값이 들어 있다.
		// db에 있는 로그인 값을 찾아 옴
		int resultGive = 0;
		MemberDTO accpterMoney = new MemberDTO();

		try {
			// 돈을 받는 사람
			String sql = "update member set mmoney = mmoney + ? where mid = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, giveMoneyDTO.getMmoney());
			// service에서 받은 id가 첫번째 ? 에 적용
			preparedStatement.setString(2, giveMoneyDTO.getMid());
			// service에서 받은 pw가 두번째 ? 에 적용
			resultGive = preparedStatement.executeUpdate();
			if (resultGive > 0) {
				System.out.println(resultGive + "행의 데이터를 변경했습니다.");
				connection.commit();
			} else {
				System.out.println("결과" + resultGive + "입니다.");
				System.out.println("회원정보수정 실패 : 롤백합니다.");
				connection.rollback();
			}
			preparedStatement.close();
			
			// 돈을 주는 사람
			String sql1 = "update member set mmoney = mmoney - ? where mid = ?";
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			preparedStatement1.setInt(1, giveMoneyDTO.getMmoney());
			// service에서 받은 id가 첫번째 ? 에 적용
			preparedStatement1.setString(2, loginMember.getMid());
			// service에서 받은 pw가 두번째 ? 에 적용
			resultGive = preparedStatement1.executeUpdate();
			if (resultGive > 0) {
				System.out.println(resultGive + "행의 데이터를 변경했습니다.");
				connection.commit();
			} else {
				System.out.println("결과" + resultGive + "입니다.");
				System.out.println("회원정보수정 실패 : 롤백합니다.");
				connection.rollback();
			}
			preparedStatement1.close();
			


		} catch (SQLException e) {
			System.out.println("찾는 id와 pw가 없습니다.");
			System.out.println("관리자 : sql 문을 확인하세요");
			System.out.println("회원은 id 와 pw를 확인하세요.");
			e.printStackTrace();
		}
	}

	public void acceptMoney() {
		
	}
	
	
} // end class
