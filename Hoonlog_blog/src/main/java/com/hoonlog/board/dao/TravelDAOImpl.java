package com.hoonlog.board.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hoonlog.board.utils.SearchCriteria;
import com.hoonlog.board.vo.TravelVO;


@Repository
public class TravelDAOImpl implements TravelDAO{
	
	@Inject
	private SqlSession sqlSession;
	
	
	/* 게시글 목록 */
	@Override
	public List<TravelVO> travelList(SearchCriteria scri) throws Exception {
		return sqlSession.selectList("travelMapper.travelListPage", scri);
	}
	
	/* 총 게시글 개수 확인 */
	@Override
	public int travelListCnt(SearchCriteria scri) throws Exception {
		return sqlSession.selectOne("travelMapper.travelListCnt", scri);
	}

	/* 게시글 내용 */
	@Override
	public TravelVO travelView(int bno) throws Exception {
		return sqlSession.selectOne("travelMapper.travelView", bno);
	}

	/* 게시글 등록 */
	@Override
	public void insertTravel(TravelVO travel) throws Exception {
		sqlSession.insert("travelMapper.insertTravel", travel);
	}

	/* 게시글 수정 */
	@Override
	public void updateTravel(TravelVO travel) throws Exception {
		sqlSession.update("travelMapper.updateTravel", travel);
	}

	/* 게시글 삭제 */
	@Override
	public void deleteTravel(int bno) throws Exception {
		sqlSession.delete("travelMapper.deleteTravel", bno);
	}

	/* 게시글 조회수 */
	@Override
	public int updateCnt(int bno) throws Exception {
		return sqlSession.update("travelMapper.updateCnt", bno);
	}

	/* 파일 업로드 */
	@Override
	public void insertFile(Map<String, Object> map) throws Exception {
		sqlSession.insert("travelMapper.insertFile", map);
	}

	/* 첨부파일 조회 */
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		return sqlSession.selectList("travelMapper.selectFileList", bno);
	}

	/* 첨부파일 다운 */
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne("travelMapper.selectFileInfo", map);
	}

	/* 첨부파일 수정 */
	@Override
	public void updateFile(Map<String, Object> map) throws Exception {
		sqlSession.update("travelMapper.updateFile", map);
	}

	
}
