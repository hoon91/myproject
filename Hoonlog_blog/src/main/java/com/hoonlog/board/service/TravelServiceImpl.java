package com.hoonlog.board.service;

import java.util.List;
import java.util.Map;


import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hoonlog.board.dao.TravelDAO;
import com.hoonlog.board.utils.SearchCriteria;
import com.hoonlog.board.utils.TravelFileUtils;
import com.hoonlog.board.vo.TravelVO;


@Service
public class TravelServiceImpl implements TravelService{
	
	@Resource(name="travelFileUtils")
	private TravelFileUtils fileUtils;
	
	@Inject
	TravelDAO travelDAO;

	/* 게시글 목록 */
	@Override
	public List<TravelVO> travelList(SearchCriteria scri) throws Exception {
		return travelDAO.travelList(scri);
	}
	
	/* 총 게시글 개수 확인 */
	@Override
	public int travelListCnt(SearchCriteria scri) throws Exception {
		return travelDAO.travelListCnt(scri);
	}

	/* 게시글 내용 / 조회수 증가 */
	@Override
	public TravelVO travelView(int bno) throws Exception {
		travelDAO.updateCnt(bno);
		return travelDAO.travelView(bno);
	}

	/* 게시글 등록, 파일 업로드 */
	@Override
	public void insertTravel(TravelVO travel, MultipartHttpServletRequest mpRequest) throws Exception {
		travelDAO.insertTravel(travel);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(travel, mpRequest); 
		int size = list.size();
		for(int i=0; i<size; i++){ 
			travelDAO.insertFile(list.get(i)); 
		}
	}
		
	/* 게시글 수정, 첨부파일 수정 */
	@Override
	public void updateTravel(TravelVO travel, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception {
		travelDAO.updateTravel(travel);
	
		List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(travel, files, fileNames, mpRequest);
		Map<String, Object> tempMap = null;
		int size = list.size();
		for(int i = 0; i<size; i++) {
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")) {
				travelDAO.insertFile(tempMap);
			}else {
				travelDAO.updateFile(tempMap);
			}
		}
		
	}
	
	/* 게시글 삭제 */
	@Override
	public void deleteTravel(int bno) throws Exception {
		travelDAO.deleteTravel(bno);
	}
	
	/* 첨부파일 조회 */
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		return travelDAO.selectFileList(bno);
	}
	
	/* 첨부파일 다운 */
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return travelDAO.selectFileInfo(map);
	}

}
