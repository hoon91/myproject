package com.hoonlog.member.dao;

import com.hoonlog.member.vo.MemberVO;

public interface MemberDAO {
	
	/* 회원가입 */
	public void signup(MemberVO member) throws Exception;
	
	/* 아이디 중복확인 */
	public int idCheck(String userid) throws Exception;
	
	/* 로그인 */
	public MemberVO login(MemberVO member) throws Exception;
	
	/* 내 정보 보기 */
	public MemberVO userInfo(String userid) throws Exception;
	
	/* 내 정보 수정 */
	public void updateUserInfo(MemberVO member) throws Exception;
	
	/* 회원 탈퇴 */
	public void deleteUser(MemberVO member) throws Exception;
	
	/* 비밀번호 변경*/
	public void changePw(MemberVO member) throws Exception;
}
