package com.hoonlog.board.service;

import java.util.List;
import java.util.Map;


import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hoonlog.board.dao.DiaryDAO;
import com.hoonlog.board.utils.DiaryFileUtils;
import com.hoonlog.board.utils.SearchCriteria;
import com.hoonlog.board.vo.DiaryVO;


@Service
public class DiaryServiceImpl implements DiaryService{
	
	@Resource(name="diaryFileUtils")
	private DiaryFileUtils fileUtils;
	
	@Inject
	DiaryDAO diaryDAO;

	/* 게시글 목록 */
	@Override
	public List<DiaryVO> diaryList(SearchCriteria scri) throws Exception {
		return diaryDAO.diaryList(scri);
	}
	
	/* 총 게시글 개수 확인 */
	@Override
	public int diaryListCnt(SearchCriteria scri) throws Exception {
		return diaryDAO.diaryListCnt(scri);
	}

	/* 게시글 내용 / 조회수 증가 */
	@Override
	public DiaryVO diaryView(int bno) throws Exception {
		diaryDAO.updateCnt(bno);
		return diaryDAO.diaryView(bno);
	}

	/* 게시글 등록, 파일 업로드 */
	@Override
	public void insertDiary(DiaryVO diary, MultipartHttpServletRequest mpRequest) throws Exception {
		diaryDAO.insertDiary(diary);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(diary, mpRequest); 
		int size = list.size();
		for(int i=0; i<size; i++){ 
			diaryDAO.insertFile(list.get(i)); 
		}
	}
		
	/* 게시글 수정, 첨부파일 수정 */
	@Override
	public void updateDiary(DiaryVO diary, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception {
		diaryDAO.updateDiary(diary);
	
		List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(diary, files, fileNames, mpRequest);
		Map<String, Object> tempMap = null;
		int size = list.size();
		for(int i = 0; i<size; i++) {
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")) {
				diaryDAO.insertFile(tempMap);
			}else {
				diaryDAO.updateFile(tempMap);
			}
		}
		
	}
	
	/* 게시글 삭제 */
	@Override
	public void deleteDiary(int bno) throws Exception {
		diaryDAO.deleteDiary(bno);
	}
	
	/* 첨부파일 조회 */
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		return diaryDAO.selectFileList(bno);
	}
	
	/* 첨부파일 다운 */
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return diaryDAO.selectFileInfo(map);
	}

}
