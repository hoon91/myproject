package com.hoonlog.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hoonlog.board.dao.BoardReplyDAO;
import com.hoonlog.board.vo.BoardReplyVO;

@Service
public class BoardReplyServiceImpl implements BoardReplyService {
	
	@Inject
	BoardReplyDAO replyDAO;

	@Override
	public void replyWrite(BoardReplyVO reply) throws Exception {
		replyDAO.replyWrite(reply);
	}

	@Override
	public List<BoardReplyVO> replyList(int bno) throws Exception {
		return replyDAO.replyList(bno);
	}

	@Override
	public void deleteReply(BoardReplyVO reply) throws Exception {
		replyDAO.deleteReply(reply);
	}

	@Override
	public String idCheck(int rno) throws Exception {
		return replyDAO.idCheck(rno);
	}

	@Override
	public void updateReply(BoardReplyVO reply) throws Exception {
		replyDAO.updateReply(reply);
	}

	
}
