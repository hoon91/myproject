package com.hoonlog.board.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoonlog.board.utils.SearchCriteria;
import com.hoonlog.board.vo.DiaryVO;


@Repository
public class DiaryDAOImpl implements DiaryDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	
	/* 게시글 목록 */
	@Override
	public List<DiaryVO> diaryList(SearchCriteria scri) throws Exception {
		return sqlSession.selectList("diaryMapper.diaryListPage", scri);
	}
	
	/* 총 게시글 개수 확인 */
	@Override
	public int diaryListCnt(SearchCriteria scri) throws Exception {
		return sqlSession.selectOne("diaryMapper.diaryListCnt", scri);
	}

	/* 게시글 내용 */
	@Override
	public DiaryVO diaryView(int bno) throws Exception {
		return sqlSession.selectOne("diaryMapper.diaryView", bno);
	}

	/* 게시글 등록 */
	@Override
	public void insertDiary(DiaryVO diary) throws Exception {
		sqlSession.insert("diaryMapper.insertDiary", diary);
	}

	/* 게시글 수정 */
	@Override
	public void updateDiary(DiaryVO diary) throws Exception {
		sqlSession.update("diaryMapper.updateDiary", diary);
	}

	/* 게시글 삭제 */
	@Override
	public void deleteDiary(int bno) throws Exception {
		sqlSession.delete("diaryMapper.deleteDiary", bno);
	}

	/* 게시글 조회수 */
	@Override
	public int updateCnt(int bno) throws Exception {
		return sqlSession.update("diaryMapper.updateCnt", bno);
	}

	/* 파일 업로드 */
	@Override
	public void insertFile(Map<String, Object> map) throws Exception {
		sqlSession.insert("diaryMapper.insertFile", map);
	}

	/* 첨부파일 조회 */
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		return sqlSession.selectList("diaryMapper.selectFileList", bno);
	}

	/* 첨부파일 다운 */
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne("diaryMapper.selectFileInfo", map);
	}

	/* 첨부파일 수정 */
	@Override
	public void updateFile(Map<String, Object> map) throws Exception {
		sqlSession.update("diaryMapper.updateFile", map);
	}

	
}
