package com.hoonlog.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoonlog.board.vo.DiaryReplyVO;

@Repository
public class DiaryReplyDAOImpl implements DiaryReplyDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public void replyWrite(DiaryReplyVO reply) throws Exception {
		sqlSession.insert("diaryReplyMapper.diaryReplyWrite", reply);
	}

	@Override
	public List<DiaryReplyVO> replyList(int bno) throws Exception {
		return sqlSession.selectList("diaryReplyMapper.diaryReplyList", bno);
	}

	@Override
	public void deleteReply(DiaryReplyVO reply) throws Exception {
		sqlSession.delete("diaryReplyMapper.diaryDeleteReply", reply);
	}

	@Override
	public String idCheck(int rno) throws Exception {
		return sqlSession.selectOne("diaryReplyMapper.diaryReplyUserIdCheck", rno);
	}

	@Override
	public void updateReply(DiaryReplyVO reply) throws Exception {
		sqlSession.update("diaryReplyMapper.diaryUpdateReply", reply);
	}

	
	
	
}
