package com.hoonlog.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hoonlog.board.utils.SearchCriteria;
import com.hoonlog.board.vo.ItVO;


public interface ItService {
	
	/* 게시글 목록 */
	public List<ItVO> itList(SearchCriteria scri) throws Exception;
	
	/* 총 게시글 개수 확인 */
	public int itListCnt(SearchCriteria scri) throws Exception;
	
	/* 게시글 내용 */
	public ItVO itView(int bno) throws Exception;
	
	/* 게시글 등록 */
	public void insertIt(ItVO it, MultipartHttpServletRequest mpRequest) throws Exception;

	/* 게시글 수정 */
	public void updateIt(ItVO it, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception;
	
	/* 게시글 삭제 */
	public void deleteIt(int bno) throws Exception;
	
	/* 첨부파일 조회 */
	public List<Map<String, Object>> selectFileList(int bno) throws Exception;
	    
	/* 첨부파일 다운 */
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;
	    
}
