package com.hoonlog.board.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoonlog.board.utils.SearchCriteria;
import com.hoonlog.board.vo.MovieVO;


@Repository
public class MovieDAOImpl implements MovieDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	
	/* 게시글 목록 */
	@Override
	public List<MovieVO> movieList(SearchCriteria scri) throws Exception {
		return sqlSession.selectList("movieMapper.movieListPage", scri);
	}
	
	/* 총 게시글 개수 확인 */
	@Override
	public int movieListCnt(SearchCriteria scri) throws Exception {
		return sqlSession.selectOne("movieMapper.movieListCnt", scri);
	}

	/* 게시글 내용 */
	@Override
	public MovieVO movieView(int bno) throws Exception {
		return sqlSession.selectOne("movieMapper.movieView", bno);
	}

	/* 게시글 등록 */
	@Override
	public void insertMovie(MovieVO movie) throws Exception {
		sqlSession.insert("movieMapper.insertMovie", movie);
	}

	/* 게시글 수정 */
	@Override
	public void updateMovie(MovieVO movie) throws Exception {
		sqlSession.update("movieMapper.updateMovie", movie);
	}

	/* 게시글 삭제 */
	@Override
	public void deleteMovie(int bno) throws Exception {
		sqlSession.delete("movieMapper.deleteMovie", bno);
	}

	/* 게시글 조회수 */
	@Override
	public int updateCnt(int bno) throws Exception {
		return sqlSession.update("movieMapper.updateCnt", bno);
	}

	/* 파일 업로드 */
	@Override
	public void insertFile(Map<String, Object> map) throws Exception {
		sqlSession.insert("movieMapper.insertFile", map);
	}

	/* 첨부파일 조회 */
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		return sqlSession.selectList("movieMapper.selectFileList", bno);
	}

	/* 첨부파일 다운 */
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne("movieMapper.selectFileInfo", map);
	}

	/* 첨부파일 수정 */
	@Override
	public void updateFile(Map<String, Object> map) throws Exception {
		sqlSession.update("movieMapper.updateFile", map);
	}

	
}
