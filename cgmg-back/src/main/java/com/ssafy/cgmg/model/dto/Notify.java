package com.ssafy.cgmg.model.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "알림 DTO")
public class Notify {

	private int id;
	private String userId;
	private String causedBy;
	private int postId;
	private String message;
	private String notType;
	private String regDate;
	private String isChecked;

	public Notify() {
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

	public String getCausedBy() {
		return causedBy;
	}

	public void setCausedBy(String causedBy) {
		this.causedBy = causedBy;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNotType() {
		return notType;
	}

	public void setNotType(String notType) {
		this.notType = notType;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

	@Override
	public String toString() {
		return "Notify [id=" + id + ", userId=" + userId + ", causedBy=" + causedBy + ", postId=" + postId
				+ ", message=" + message + ", notType=" + notType + ", regDate=" + regDate + ", isChecked=" + isChecked
				+ "]";
	}

}
