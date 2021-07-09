package com.hoonlog.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoonlog.board.vo.BoardReplyVO;

@Repository
public class BoardReplyDAOImpl implements BoardReplyDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public void replyWrite(BoardReplyVO reply) throws Exception {
		sqlSession.insert("boardReplyMapper.boardReplyWrite", reply);
	}

	@Override
	public List<BoardReplyVO> replyList(int bno) throws Exception {
		return sqlSession.selectList("boardReplyMapper.boardReplyList", bno);
	}

	@Override
	public void deleteReply(BoardReplyVO reply) throws Exception {
		sqlSession.delete("boardReplyMapper.boardDeleteReply", reply);
	}

	@Override
	public String idCheck(int rno) throws Exception {
		return sqlSession.selectOne("boardReplyMapper.boardReplyUserIdCheck", rno);
	}

	@Override
	public void updateReply(BoardReplyVO reply) throws Exception {
		sqlSession.update("boardReplyMapper.boardUpdateReply", reply);
	}

	
	
	
}
