package com.ssafy.cgmg.model.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "댓글 DTO")
public class Comment {

	private int id; // 댓글 번호
	private int postId; // 게시글 번호
	private int parentId; // 부모 댓글 번호
	private String writer; // 작성자
	private String content; // 댓글 내용
	private String regDate; // 작성일자
	private String isDeleted; // 삭제 유무
	private List<Comment> children; // 자식 댓글 리스트

	public Comment() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<Comment> getChildren() {
		return children;
	}

	public void setChildren(List<Comment> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", postId=" + postId + ", parentId=" + parentId + ", writer=" + writer
				+ ", content=" + content + ", regDate=" + regDate + ", isDeleted=" + isDeleted + ", children="
				+ children + "]";
	}

}
