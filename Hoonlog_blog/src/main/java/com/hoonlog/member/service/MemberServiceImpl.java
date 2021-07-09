package com.hoonlog.member.service;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.hoonlog.member.dao.MemberDAO;
import com.hoonlog.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject
	MemberDAO memberDAO;
	/* 회원가입 */
	@Override
	public void signup(MemberVO member) throws Exception {
		memberDAO.signup(member);
	}
	
	/* 아이디 중복확인 */
	@Override
	public int idCheck(String userid) throws Exception {
		return memberDAO.idCheck(userid);
	}
	
	/* 내 정보 보기 */
	@Override
	public MemberVO userInfo(String userid) throws Exception {
		return memberDAO.userInfo(userid);
	}
	

	@Override
	public MemberVO updateUserForm(String userid) throws Exception {
		return memberDAO.userInfo(userid);
	}

	@Override
	public void updateUserInfo(MemberVO member) throws Exception {
		memberDAO.updateUserInfo(member);
	}

	@Override
	public void deleteUser(MemberVO member, HttpSession session) throws Exception {
		memberDAO.deleteUser(member);
		session.invalidate();
	}

	@Override
	public MemberVO login(MemberVO member) throws Exception {
		return memberDAO.login(member);
	}

	@Override
	public void logout(HttpSession session) throws Exception {
		session.invalidate();
	}

	@Override
	public void changePw(MemberVO member, HttpSession session) throws Exception {
		memberDAO.changePw(member);
		session.invalidate();
	}
	
	
}
