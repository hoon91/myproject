package com.hoonlog.board.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoonlog.board.utils.SearchCriteria;
import com.hoonlog.board.vo.ItVO;



@Repository
public class ItDAOImpl implements ItDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	
	/* 게시글 목록 */
	@Override
	public List<ItVO> itList(SearchCriteria scri) throws Exception {
		return sqlSession.selectList("itMapper.itListPage", scri);
	}
	
	/* 총 게시글 개수 확인 */
	@Override
	public int itListCnt(SearchCriteria scri) throws Exception {
		return sqlSession.selectOne("itMapper.itListCnt", scri);
	}

	/* 게시글 내용 */
	@Override
	public ItVO itView(int bno) throws Exception {
		return sqlSession.selectOne("itMapper.itView", bno);
	}

	/* 게시글 등록 */
	@Override
	public void insertIt(ItVO it) throws Exception {
		sqlSession.insert("itMapper.insertIt", it);
	}

	/* 게시글 수정 */
	@Override
	public void updateIt(ItVO it) throws Exception {
		sqlSession.update("itMapper.updateIt", it);
	}

	/* 게시글 삭제 */
	@Override
	public void deleteIt(int bno) throws Exception {
		sqlSession.delete("itMapper.deleteIt", bno);
	}

	/* 게시글 조회수 */
	@Override
	public int updateCnt(int bno) throws Exception {
		return sqlSession.update("itMapper.updateCnt", bno);
	}

	/* 파일 업로드 */
	@Override
	public void insertFile(Map<String, Object> map) throws Exception {
		sqlSession.insert("itMapper.insertFile", map);
	}

	/* 첨부파일 조회 */
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		return sqlSession.selectList("itMapper.selectFileList", bno);
	}

	/* 첨부파일 다운 */
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne("itMapper.selectFileInfo", map);
	}

	/* 첨부파일 수정 */
	@Override
	public void updateFile(Map<String, Object> map) throws Exception {
		sqlSession.update("itMapper.updateFile", map);
	}

	
}
