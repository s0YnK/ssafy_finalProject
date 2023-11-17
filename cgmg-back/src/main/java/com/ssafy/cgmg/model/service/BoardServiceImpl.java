package com.ssafy.cgmg.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.cgmg.model.dao.BoardDao;
import com.ssafy.cgmg.model.dto.Board;
import com.ssafy.cgmg.model.dto.LikeLog;
import com.ssafy.cgmg.model.dto.SearchCondition;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	@Override
	public List<Board> getList() {
		return boardDao.selectAll();
	}

	@Override
	public Board getBoard(int id) {
		boardDao.updateViewCnt(id);
		return boardDao.selectOne(id);
	}
	
	@Override
	public List<Board> getListByUserId(String userId) {
		return boardDao.selectByUserId(userId);
	}
	
	@Transactional
	@Override
	public int writeBoard(Board board) {
		return boardDao.insertBoard(board);
	}

	@Transactional
	@Override
	public int modifyBoard(Board board) {
		return boardDao.updateBoard(board);
	}

	@Transactional
	@Override
	public int removeBoard(int id) {
		return boardDao.deleteBoard(id);
	}


	@Override
	public List<Board> search(SearchCondition searchCondition) {
		return boardDao.search(searchCondition);
	}

	@Override
	public int writeLikeLog(LikeLog likeLog) {
		likeLog.setCntChange("up");
		boardDao.updateLikeCnt(likeLog);
		return boardDao.insertLikeLog(likeLog);
	}

	@Override
	public int removeLikeLog(LikeLog likeLog) {
		likeLog.setCntChange("down");
		boardDao.updateLikeCnt(likeLog);
		return boardDao.deleteLikeLog(likeLog);
	}

	@Override
	public List<Board> getLikedBoard(String userId) {
		return boardDao.selectLikedBoard(userId);
	}

}
