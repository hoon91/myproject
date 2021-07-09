package com.hoonlog.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hoonlog.board.dao.DiaryReplyDAO;
import com.hoonlog.board.vo.DiaryReplyVO;


@Service
public class DiaryReplyServiceImpl implements DiaryReplyService {
	
	@Inject
	DiaryReplyDAO replyDAO;

	@Override
	public void replyWrite(DiaryReplyVO reply) throws Exception {
		replyDAO.replyWrite(reply);
	}

	@Override
	public List<DiaryReplyVO> replyList(int bno) throws Exception {
		return replyDAO.replyList(bno);
	}

	@Override
	public void deleteReply(DiaryReplyVO reply) throws Exception {
		replyDAO.deleteReply(reply);
	}

	@Override
	public String idCheck(int rno) throws Exception {
		return replyDAO.idCheck(rno);
	}

	@Override
	public void updateReply(DiaryReplyVO reply) throws Exception {
		replyDAO.updateReply(reply);
	}

	
}
