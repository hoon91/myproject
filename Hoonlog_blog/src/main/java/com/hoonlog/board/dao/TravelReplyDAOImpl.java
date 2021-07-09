package com.hoonlog.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoonlog.board.vo.TravelReplyVO;

@Repository
public class TravelReplyDAOImpl implements TravelReplyDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public void replyWrite(TravelReplyVO reply) throws Exception {
		sqlSession.insert("travelReplyMapper.travelReplyWrite", reply);
	}

	@Override
	public List<TravelReplyVO> replyList(int bno) throws Exception {
		return sqlSession.selectList("travelReplyMapper.travelReplyList", bno);
	}

	@Override
	public void deleteReply(TravelReplyVO reply) throws Exception {
		sqlSession.delete("travelReplyMapper.travelDeleteReply", reply);
	}

	@Override
	public String idCheck(int rno) throws Exception {
		return sqlSession.selectOne("travelReplyMapper.travelReplyUserIdCheck", rno);
	}

	@Override
	public void updateReply(TravelReplyVO reply) throws Exception {
		sqlSession.update("travelReplyMapper.travelUpdateReply", reply);
	}

	
	
	
}
