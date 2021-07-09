package com.hoonlog.board.dao;

import java.util.List;

import com.hoonlog.board.vo.MovieReplyVO;

public interface MovieReplyDAO {
	
	public void replyWrite(MovieReplyVO reply) throws Exception;
	
    public List<MovieReplyVO> replyList(int bno) throws Exception;
    
    public void deleteReply(MovieReplyVO reply) throws Exception;
    
    public String idCheck(int rno) throws Exception;
    
    public void updateReply(MovieReplyVO reply) throws Exception;
    
    
}
