package com.hoonlog.board.service;

import java.util.List;

import com.hoonlog.board.vo.TravelReplyVO;

public interface TravelReplyService {
	
	public void replyWrite(TravelReplyVO reply) throws Exception;
	
	public List<TravelReplyVO> replyList(int bno) throws Exception;
	
	public void deleteReply(TravelReplyVO reply) throws Exception;
	    
	public String idCheck(int rno) throws Exception;

	public void updateReply(TravelReplyVO reply) throws Exception;
	
}
