package com.ssafy.cgmg.model.dao;

import java.util.List;

import com.ssafy.cgmg.model.dto.Board;
import com.ssafy.cgmg.model.dto.LikeCnt;
import com.ssafy.cgmg.model.dto.LikeLog;
import com.ssafy.cgmg.model.dto.SearchCondition;

public interface BoardDao {
	
	// 게시글 전체 조회
	public List<Board> selectAll();

	// 게시글 상세 조회
	public Board selectOne(int id);
	
	// 내가 작성한 게시글 목록 조회
	public List<Board> selectByUserId(String userId);

	// 게시글 등록
	public int insertBoard(Board board);

	// 게시글 수정
	public int updateBoard(Board board);
	
	// 게시글 삭제
	public int deleteBoard(int id);

	// 조회수 증가
	public int updateViewCnt(int id);

	// 게시글 검색
	public List<Board> search(SearchCondition searchCondition);
	
	// 게시글 좋아요 등록
	public int insertLikeLog(LikeLog likeLog);
	
	// 게시글 좋아요 취소
	public int deleteLikeLog(LikeLog likeLog);
	
	// 좋아요 기록 조회
	public LikeLog selectLikeLog(LikeLog likeLog);
	
	// 게시글 좋아요수 업데이트
	public int updateLikeCnt(LikeCnt likeCnt);
	
	// 게시글 좋아요한 목록 조회
	public List<Board> selectLikedBoard(String userId);
	
	// 회원탈퇴 시 좋아요 기록 모두 삭제
	public int deleteAllLikeLog(String userId);
	
	// 회원탈퇴 시 작성한 게시글 모두 삭제
	public int deleteAllBoard(String userId);
	
	// 게시글 삭제 시 좋아요 기록 모두 삭제
	public int deleteAllLikeLog2(int postId);

}
