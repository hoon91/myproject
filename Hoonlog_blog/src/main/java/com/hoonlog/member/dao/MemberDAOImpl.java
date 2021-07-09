package com.hoonlog.member.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoonlog.member.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO	{
	
	@Inject
	SqlSession sqlSession;

	/* 회원가입 */
	@Override
	public void signup(MemberVO member) throws Exception {
		sqlSession.insert("memberMapper.signup", member);
	}
	
	/* 아이디 중복확인 */
	@Override
	public int idCheck(String userid) throws Exception {
		MemberVO member = sqlSession.selectOne("memberMapper.userInfo", userid);
		
		/* userid로 검색해서 DB에 저장된 내용이 있으면 1, 없으면 0 반환 */
		if(member != null) {
			return 1;
		} else {
			return 0;
		} 
	}
	
	/* 로그인 */
	@Override
	public MemberVO login(MemberVO member) throws Exception {
		return sqlSession.selectOne("memberMapper.login", member);
	}
	
	/* 내 정보 보기 */
	@Override
	public MemberVO userInfo(String userid) throws Exception {
		return sqlSession.selectOne("memberMapper.userInfo", userid);
	}
	
	/* 내 정보 수정 */
	@Override
	public void updateUserInfo(MemberVO member) throws Exception {
		sqlSession.update("memberMapper.updateUserInfo", member);
	}
	
	/* 회원 탈퇴 */
	@Override
	public void deleteUser(MemberVO member) throws Exception {
		sqlSession.delete("memberMapper.deleteUser", member);
	}
	
	/* 비밀번호 변경*/
	@Override
	public void changePw(MemberVO member) throws Exception {
		sqlSession.update("memberMapper.changePw", member);
	}

	
}
