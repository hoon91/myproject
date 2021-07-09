package com.hoonlog.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hoonlog.board.dao.ItReplyDAO;
import com.hoonlog.board.vo.ItReplyVO;

@Service
public class ItReplyServiceImpl implements ItReplyService {
	
	@Inject
	ItReplyDAO replyDAO;

	@Override
	public void replyWrite(ItReplyVO reply) throws Exception {
		replyDAO.replyWrite(reply);
	}

	@Override
	public List<ItReplyVO> replyList(int bno) throws Exception {
		return replyDAO.replyList(bno);
	}

	@Override
	public void deleteReply(ItReplyVO reply) throws Exception {
		replyDAO.deleteReply(reply);
	}

	@Override
	public String idCheck(int rno) throws Exception {
		return replyDAO.idCheck(rno);
	}

	@Override
	public void updateReply(ItReplyVO reply) throws Exception {
		replyDAO.updateReply(reply);
	}

	
}
