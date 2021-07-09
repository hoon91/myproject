package com.hoonlog.board.service;

import java.util.List;
import java.util.Map;


import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hoonlog.board.dao.BoardDAO;
import com.hoonlog.board.utils.BoardFileUtils;
import com.hoonlog.board.utils.SearchCriteria;
import com.hoonlog.board.vo.BoardVO;


@Service
public class BoardServiceImpl implements BoardService{
	
	@Resource(name="boardFileUtils")
	private BoardFileUtils fileUtils;
	
	@Inject
	BoardDAO boardDAO;

	/* 게시글 목록 */
	@Override
	public List<BoardVO> boardList(SearchCriteria scri) throws Exception {
		return boardDAO.boardList(scri);
	}
	
	/* 총 게시글 개수 확인 */
	@Override
	public int boardListCnt(SearchCriteria scri) throws Exception {
		return boardDAO.boardListCnt(scri);
	}

	/* 게시글 내용 / 조회수 증가 */
	@Override
	public BoardVO boardView(int bno) throws Exception {
		boardDAO.updateCnt(bno);
		return boardDAO.boardView(bno);
	}

	/* 게시글 등록, 파일 업로드 */
	@Override
	public void insertBoard(BoardVO board, MultipartHttpServletRequest mpRequest) throws Exception {
		boardDAO.insertBoard(board);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(board, mpRequest); 
		int size = list.size();
		for(int i=0; i<size; i++){ 
			boardDAO.insertFile(list.get(i)); 
		}
	}
		
	/* 게시글 수정, 첨부파일 수정 */
	@Override
	public void updateBoard(BoardVO board, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception {
		boardDAO.updateBoard(board);
	
		List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(board, files, fileNames, mpRequest);
		Map<String, Object> tempMap = null;
		int size = list.size();
		for(int i = 0; i<size; i++) {
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")) {
				boardDAO.insertFile(tempMap);
			}else {
				boardDAO.updateFile(tempMap);
			}
		}
		
	}
	
	/* 게시글 삭제 */
	@Override
	public void deleteBoard(int bno) throws Exception {
		boardDAO.deleteBoard(bno);
	}
	
	/* 첨부파일 조회 */
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		return boardDAO.selectFileList(bno);
	}
	
	/* 첨부파일 다운 */
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return boardDAO.selectFileInfo(map);
	}

	/*@Override
	public void updateReplyCount(int bno) throws Exception {
		boardDAO.updateReplyCount(bno);
	}
*/
}
