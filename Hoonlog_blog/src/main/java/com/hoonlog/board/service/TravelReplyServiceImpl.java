package com.hoonlog.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hoonlog.board.dao.TravelReplyDAO;
import com.hoonlog.board.vo.TravelReplyVO;

@Service
public class TravelReplyServiceImpl implements TravelReplyService {
	
	@Inject
	TravelReplyDAO replyDAO;

	@Override
	public void replyWrite(TravelReplyVO reply) throws Exception {
		replyDAO.replyWrite(reply);
	}

	@Override
	public List<TravelReplyVO> replyList(int bno) throws Exception {
		return replyDAO.replyList(bno);
	}

	@Override
	public void deleteReply(TravelReplyVO reply) throws Exception {
		replyDAO.deleteReply(reply);
	}

	@Override
	public String idCheck(int rno) throws Exception {
		return replyDAO.idCheck(rno);
	}

	@Override
	public void updateReply(TravelReplyVO reply) throws Exception {
		replyDAO.updateReply(reply);
	}

	
}
