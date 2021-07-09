package com.hoonlog.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoonlog.board.vo.ItReplyVO;


@Repository
public class ItReplyDAOImpl implements ItReplyDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public void replyWrite(ItReplyVO reply) throws Exception {
		sqlSession.insert("itReplyMapper.itReplyWrite", reply);
	}

	@Override
	public List<ItReplyVO> replyList(int bno) throws Exception {
		return sqlSession.selectList("itReplyMapper.itReplyList", bno);
	}

	@Override
	public void deleteReply(ItReplyVO reply) throws Exception {
		sqlSession.delete("itReplyMapper.itDeleteReply", reply);
	}

	@Override
	public String idCheck(int rno) throws Exception {
		return sqlSession.selectOne("itReplyMapper.itReplyUserIdCheck", rno);
	}

	@Override
	public void updateReply(ItReplyVO reply) throws Exception {
		sqlSession.update("itReplyMapper.itUpdateReply", reply);
	}

	
	
	
}
