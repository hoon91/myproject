package com.hoonlog.board.dao;

import java.util.List;
import java.util.Map;

import com.hoonlog.board.vo.TravelVO;
import com.hoonlog.board.utils.SearchCriteria;



public interface TravelDAO {
	
	/* 게시글 목록 */
	public List<TravelVO> travelList(SearchCriteria scri) throws Exception;

	/* 총 게시글 개수 확인 */
	public int travelListCnt(SearchCriteria scri) throws Exception;

	/* 게시글 내용 */
	public TravelVO travelView(int bno) throws Exception;
	
	/* 게시글 등록 */
	public void insertTravel(TravelVO travel) throws Exception;

	/* 게시글 수정 */
	public void updateTravel(TravelVO travel) throws Exception;
	
	/* 게시글 삭제 */
	public void deleteTravel(int bno) throws Exception;
	
	/* 게시글 조회수 */
	public int updateCnt(int bno) throws Exception;
	
	/* 파일 업로드 */
	public void insertFile(Map<String, Object> map) throws Exception;
	
	/* 첨부파일 조회 */
	public List<Map<String, Object>> selectFileList(int bno) throws Exception;
		
	/* 첨부파일 다운 */
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;
	    
	/* 첨부파일 수정 */
	public void updateFile(Map<String, Object> map) throws Exception;
	

}
