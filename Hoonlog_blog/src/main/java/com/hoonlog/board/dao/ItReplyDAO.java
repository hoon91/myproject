package com.hoonlog.board.dao;

import java.util.List;

import com.hoonlog.board.vo.ItReplyVO;


public interface ItReplyDAO {
	
	public void replyWrite(ItReplyVO reply) throws Exception;
	
    public List<ItReplyVO> replyList(int bno) throws Exception;
    
    public void deleteReply(ItReplyVO reply) throws Exception;
    
    public String idCheck(int rno) throws Exception;
    
    public void updateReply(ItReplyVO reply) throws Exception;
    
    
}
