package com.ssafy.cgmg.model.dao;

import java.util.List;

import com.ssafy.cgmg.model.dto.Comment;

public interface CommentDao {
	
	// 댓글 리스트 조회
	public List<Comment> selectComment(int postId);
	
	// 댓글 등록
	public int insertComment(Comment comment);
	
	// 댓글 수정
	public int updateComment(Comment comment);
	
	// 삭제 상태 업데이트
	public int updateIsDeleted(Comment comment);
	
	// 댓글 삭제
	public int deleteComment(int id);
	
	// 댓글 1개 조회
	public Comment selectOne(int id);
	
	// 자식 댓글 리스트 조회
	public List<Comment> selectChildren(int id);
	
	// 회원탈퇴 시 댓글 모두 삭제
	public int deleteAllComment(String userId);

}
