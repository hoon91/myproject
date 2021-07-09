package com.hoonlog.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hoonlog.board.utils.SearchCriteria;
import com.hoonlog.board.vo.BoardVO;


public interface BoardService {
	
	/* 게시글 목록 */
	public List<BoardVO> boardList(SearchCriteria scri) throws Exception;
	
	/* 총 게시글 개수 확인 */
	public int boardListCnt(SearchCriteria scri) throws Exception;
	
	/* 게시글 내용 */
	public BoardVO boardView(int bno) throws Exception;
	
	/* 게시글 등록 */
	public void insertBoard(BoardVO board, MultipartHttpServletRequest mpRequest) throws Exception;

	/* 게시글 수정 */
	public void updateBoard(BoardVO board, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception;
	
	/* 게시글 삭제 */
	public void deleteBoard(int bno) throws Exception;
	
	/* 첨부파일 조회 */
	public List<Map<String, Object>> selectFileList(int bno) throws Exception;
	    
	/* 첨부파일 다운 */
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;
	
	
}
