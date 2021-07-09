package com.hoonlog.board.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoonlog.board.utils.SearchCriteria;
import com.hoonlog.board.vo.BoardVO;


@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	/* 게시글 목록 */
	@Override
	public List<BoardVO> boardList(SearchCriteria scri) throws Exception {
		return sqlSession.selectList("boardMapper.boardListPage", scri);
	}
	/* 총 게시글 개수 확인 */
	@Override
	public int boardListCnt(SearchCriteria scri) throws Exception {
		return sqlSession.selectOne("boardMapper.boardListCnt", scri);
	}
	/* 게시글 내용 */
	@Override
	public BoardVO boardView(int bno) throws Exception {
		return sqlSession.selectOne("boardMapper.boardView", bno);
	}
	/* 게시글 등록 */
	@Override
	public void insertBoard(BoardVO board) throws Exception {
		sqlSession.insert("boardMapper.insertBoard", board);
	}
	/* 게시글 수정 */
	@Override
	public void updateBoard(BoardVO board) throws Exception {
		sqlSession.update("boardMapper.updateBoard", board);
	}
	/* 게시글 삭제 */
	@Override
	public void deleteBoard(int bno) throws Exception {
		sqlSession.delete("boardMapper.deleteBoard", bno);
	}
	/* 게시글 조회수 */
	@Override
	public int updateCnt(int bno) throws Exception {
		return sqlSession.update("boardMapper.updateCnt", bno);
	}
	/* 파일 업로드 */
	@Override
	public void insertFile(Map<String, Object> map) throws Exception {
		sqlSession.insert("boardMapper.insertFile", map);
	}
	/* 첨부파일 조회 */
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		return sqlSession.selectList("boardMapper.selectFileList", bno);
	}
	/* 첨부파일 다운 */
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne("boardMapper.selectFileInfo", map);
	}
	/* 첨부파일 수정 */
	@Override
	public void updateFile(Map<String, Object> map) throws Exception {
		sqlSession.update("boardMapper.updateFile", map);
	}
}