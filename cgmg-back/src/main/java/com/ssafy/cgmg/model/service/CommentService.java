package com.ssafy.cgmg.model.service;

import java.util.List;

import com.ssafy.cgmg.model.dto.Comment;

public interface CommentService {
	
	// 댓글 조회
	List<Comment> getCommentList(int postId);
	
	// 댓글 등록
	int writeComment(Comment comment);
	
	// 댓글 수정
	int modifyComment(Comment comment);
	
	// 댓글 삭제.
	// 자식 댓글이 없으면 완전 삭제, 있으면 isDeleted 칼럼 값만 true로 변경
	// 완전 삭제할 때 이미 삭제상태인 부모댓글 있으면 같이 삭제(재귀로 조상댓글까지 확인)
	void removeComment(Comment comment);

}
