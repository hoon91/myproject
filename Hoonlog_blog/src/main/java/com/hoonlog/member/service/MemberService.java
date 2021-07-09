package com.hoonlog.member.service;

import javax.servlet.http.HttpSession;

import com.hoonlog.member.vo.MemberVO;

public interface MemberService {
	
	/* 회원가입 */
	public void signup(MemberVO member) throws Exception;
	
	/* 아이디 중복확인 */
	public int idCheck(String userid) throws Exception;
	
	/* 로그인 */
	public MemberVO login(MemberVO member) throws Exception;
	
	/* 로그아웃 */
	public void logout(HttpSession session) throws Exception;
	
	/* 내 정보 보기 */
	public MemberVO userInfo(String userid) throws Exception;
	
	/* 내 정보 수정 폼 */
	public MemberVO updateUserForm(String userid) throws Exception;
	
	/* 내 정보 수정 */
	public void updateUserInfo(MemberVO member) throws Exception;
	
	/* 회원 탈퇴 */
	public void deleteUser(MemberVO member, HttpSession session) throws Exception;
	
	/* 비밀번호 변경 */
	public void changePw(MemberVO member, HttpSession session) throws Exception;
	
}
