package com.hoonlog.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hoonlog.board.dao.MovieReplyDAO;
import com.hoonlog.board.vo.MovieReplyVO;

@Service
public class MovieReplyServiceImpl implements MovieReplyService {
	
	@Inject
	MovieReplyDAO replyDAO;

	@Override
	public void replyWrite(MovieReplyVO reply) throws Exception {
		replyDAO.replyWrite(reply);
	}

	@Override
	public List<MovieReplyVO> replyList(int bno) throws Exception {
		return replyDAO.replyList(bno);
	}

	@Override
	public void deleteReply(MovieReplyVO reply) throws Exception {
		replyDAO.deleteReply(reply);
	}

	@Override
	public String idCheck(int rno) throws Exception {
		return replyDAO.idCheck(rno);
	}

	@Override
	public void updateReply(MovieReplyVO reply) throws Exception {
		replyDAO.updateReply(reply);
	}

	
}
