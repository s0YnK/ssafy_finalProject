package com.ssafy.cgmg.model.service;

import java.util.List;

import com.ssafy.cgmg.model.dto.Board;
import com.ssafy.cgmg.model.dto.LikeLog;
import com.ssafy.cgmg.model.dto.SearchCondition;

public interface BoardService {

	// 게시글 전체 조회
	List<Board> getList();

	// 게시글 상세 조회
	Board getBoard(int id);

	// 내가 작성한 게시글 목록 조회
	List<Board> getListByUserId(String userId);

	// 게시글 등록
	int writeBoard(Board board);

	// 게시글 수정
	int modifyBoard(Board board);

	// 게시글 삭제
	int removeBoard(int id);

	// 게시글 검색
	List<Board> search(SearchCondition condition);
	
	// 게시글 좋아요 등록
	int writeLikeLog(LikeLog likeLog);
	
	// 게시글 좋아요 취소
	int removeLikeLog(LikeLog likeLog);
	
	// 게시글 좋아요 기록 조회
	boolean getLikeLog(LikeLog likeLog);
	
	// 게시글 좋아요 목록 조회
	List<Board> getLikedBoard(String userId);

}
