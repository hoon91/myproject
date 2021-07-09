package com.hoonlog.board.dao;

import java.util.List;

import com.hoonlog.board.vo.BoardReplyVO;

public interface BoardReplyDAO {
	
	public void replyWrite(BoardReplyVO reply) throws Exception;
	
    public List<BoardReplyVO> replyList(int bno) throws Exception;
    
    public void deleteReply(BoardReplyVO reply) throws Exception;
    
    public String idCheck(int rno) throws Exception;
    
    public void updateReply(BoardReplyVO reply) throws Exception;
    
    
}
