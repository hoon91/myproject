package com.hoonlog.board.service;

import java.util.List;
import java.util.Map;


import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hoonlog.board.dao.MovieDAO;
import com.hoonlog.board.utils.MovieFileUtils;
import com.hoonlog.board.utils.SearchCriteria;
import com.hoonlog.board.vo.MovieVO;


@Service
public class MovieServiceImpl implements MovieService{
	
	@Resource(name="movieFileUtils")
	private MovieFileUtils fileUtils;
	
	@Inject
	MovieDAO movieDAO;

	/* 게시글 목록 */
	@Override
	public List<MovieVO> movieList(SearchCriteria scri) throws Exception {
		return movieDAO.movieList(scri);
	}
	
	/* 총 게시글 개수 확인 */
	@Override
	public int movieListCnt(SearchCriteria scri) throws Exception {
		return movieDAO.movieListCnt(scri);
	}

	/* 게시글 내용 / 조회수 증가 */
	@Override
	public MovieVO movieView(int bno) throws Exception {
		movieDAO.updateCnt(bno);
		return movieDAO.movieView(bno);
	}

	/* 게시글 등록, 파일 업로드 */
	@Override
	public void insertMovie(MovieVO movie, MultipartHttpServletRequest mpRequest) throws Exception {
		movieDAO.insertMovie(movie);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(movie, mpRequest); 
		int size = list.size();
		for(int i=0; i<size; i++){ 
			movieDAO.insertFile(list.get(i)); 
		}
	}
		
	/* 게시글 수정, 첨부파일 수정 */
	@Override
	public void updateMovie(MovieVO movie, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception {
		movieDAO.updateMovie(movie);
	
		List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(movie, files, fileNames, mpRequest);
		Map<String, Object> tempMap = null;
		int size = list.size();
		for(int i = 0; i<size; i++) {
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")) {
				movieDAO.insertFile(tempMap);
			}else {
				movieDAO.updateFile(tempMap);
			}
		}
		
	}
	
	/* 게시글 삭제 */
	@Override
	public void deleteMovie(int bno) throws Exception {
		movieDAO.deleteMovie(bno);
	}
	
	/* 첨부파일 조회 */
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		return movieDAO.selectFileList(bno);
	}
	
	/* 첨부파일 다운 */
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return movieDAO.selectFileInfo(map);
	}

}
