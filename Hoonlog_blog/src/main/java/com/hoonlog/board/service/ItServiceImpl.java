package com.hoonlog.board.service;

import java.util.List;
import java.util.Map;


import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hoonlog.board.dao.ItDAO;
import com.hoonlog.board.utils.ItFileUtils;
import com.hoonlog.board.utils.SearchCriteria;
import com.hoonlog.board.vo.ItVO;


@Service
public class ItServiceImpl implements ItService{
	
	@Resource(name="itFileUtils")
	private ItFileUtils fileUtils;
	
	@Inject
	ItDAO itDAO;

	/* 게시글 목록 */
	@Override
	public List<ItVO> itList(SearchCriteria scri) throws Exception {
		return itDAO.itList(scri);
	}
	
	/* 총 게시글 개수 확인 */
	@Override
	public int itListCnt(SearchCriteria scri) throws Exception {
		return itDAO.itListCnt(scri);
	}

	/* 게시글 내용 / 조회수 증가 */
	@Override
	public ItVO itView(int bno) throws Exception {
		itDAO.updateCnt(bno);
		return itDAO.itView(bno);
	}

	/* 게시글 등록, 파일 업로드 */
	@Override
	public void insertIt(ItVO it, MultipartHttpServletRequest mpRequest) throws Exception {
		itDAO.insertIt(it);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(it, mpRequest); 
		int size = list.size();
		for(int i=0; i<size; i++){ 
			itDAO.insertFile(list.get(i)); 
		}
	}
		
	/* 게시글 수정, 첨부파일 수정 */
	@Override
	public void updateIt(ItVO it, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception {
		itDAO.updateIt(it);
	
		List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(it, files, fileNames, mpRequest);
		Map<String, Object> tempMap = null;
		int size = list.size();
		for(int i = 0; i<size; i++) {
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")) {
				itDAO.insertFile(tempMap);
			}else {
				itDAO.updateFile(tempMap);
			}
		}
		
	}
	
	/* 게시글 삭제 */
	@Override
	public void deleteIt(int bno) throws Exception {
		itDAO.deleteIt(bno);
	}
	
	/* 첨부파일 조회 */
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		return itDAO.selectFileList(bno);
	}
	
	/* 첨부파일 다운 */
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return itDAO.selectFileInfo(map);
	}

}
