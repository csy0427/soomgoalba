package com.mycompany.myapp06.board.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp06.board.model.vo.Board;

@Repository("boardDao")
public class BoardDao {
	@Autowired
	private SqlSession sqlSession;
	
	public List<Board> getBoardList()  throws Exception {
//		return sqlSession.selectList("BoardNS.boardlistRM");
//		return sqlSession.selectList("BoardNS.boardlistHM");
		Map<String, Object> map1 =  new HashMap<String, Object>();
		map1.put("bw", "ejkim");
//		map1.put("bc", "aaaaaaaaaa");
//		System.out.println("map1: " + map1);
		return sqlSession.selectList("BoardNS.searchBoardlist", map1);
//		return sqlSession.selectList("BoardNS.searchBoardlist2", map1);
		
//		map1.put("category", "제목");   // 작성자 // 내용 // 전체 // 제목
//		map1.put("searchValue", "ej");
//		return sqlSession.selectList("BoardNS.searchBoardlist3", map1);
	}
	
	
}
