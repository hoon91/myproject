package com.hoonlog.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoonlog.board.vo.MovieReplyVO;

@Repository
public class MovieReplyDAOImpl implements MovieReplyDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public void replyWrite(MovieReplyVO reply) throws Exception {
		sqlSession.insert("movieReplyMapper.movieReplyWrite", reply);
	}

	@Override
	public List<MovieReplyVO> replyList(int bno) throws Exception {
		return sqlSession.selectList("movieReplyMapper.movieReplyList", bno);
	}

	@Override
	public void deleteReply(MovieReplyVO reply) throws Exception {
		sqlSession.delete("moviceReplyMapper.movieDeleteReply", reply);
	}

	@Override
	public String idCheck(int rno) throws Exception {
		return sqlSession.selectOne("movieReplyMapper.movieReplyUserIdCheck", rno);
	}

	@Override
	public void updateReply(MovieReplyVO reply) throws Exception {
		sqlSession.update("movieReplyMapper.movieUpdateReply", reply);
	}

	
	
	
}
