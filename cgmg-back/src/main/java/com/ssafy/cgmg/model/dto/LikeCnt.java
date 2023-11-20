package com.ssafy.cgmg.model.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(value="좋아요수 업데이트 DTO")
public class LikeCnt {

	private int postId; // 좋아요 된 게시글 id
	private String cntChange; // 좋아요수 변화

	public LikeCnt() {
	}

	public LikeCnt(int postId, String cntChange) {
		this.postId = postId;
		this.cntChange = cntChange;
	}
	
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getCntChange() {
		return cntChange;
	}

	public void setCntChange(String cntChange) {
		this.cntChange = cntChange;
	}

	@Override
	public String toString() {
		return "likeCnt [postId=" + postId + ", cntChange=" + cntChange + "]";
	}

}
