package com.ssafy.cgmg.model.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "좋아요 DTO")
public class LikeLog {

	private int id; // 좋아요 기록 번호
	private String userId; // 좋아요한 회원 id
	private int postId; // 좋아요 된 게시글 id

	public LikeLog() {
	}

	public LikeLog(String userId, int postId, String cntChange) {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	@Override
	public String toString() {
		return "LikeLog [id=" + id + ", userId=" + userId + ", postId=" + postId + "]";
	}

}
