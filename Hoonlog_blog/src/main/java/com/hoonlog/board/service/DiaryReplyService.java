package com.hoonlog.board.service;

import java.util.List;

import com.hoonlog.board.vo.DiaryReplyVO;

public interface DiaryReplyService {
	
	public void replyWrite(DiaryReplyVO reply) throws Exception;
	
	public List<DiaryReplyVO> replyList(int bno) throws Exception;
	
	public void deleteReply(DiaryReplyVO reply) throws Exception;
	    
	public String idCheck(int rno) throws Exception;

	public void updateReply(DiaryReplyVO reply) throws Exception;
	
}
