package com.ssafy.cgmg.model.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "팔로우 DTO")
public class FollowLog {

	private String UserId; // 회원 아이디
	private String followingId; // 팔로잉 아이디
	private String followerId; // 팔로워 아이디
	private String nickName; // 팔로잉 or 팔로워의 닉네임
	private String totalExerciseCnt; // 팔로잉 or 팔로워의 총 운동횟수 -> 티어로 변환
	private String profileImg; // 팔로잉 or 팔로워의 프로필이미지 경로
	private String cntChange; // 팔로잉수 or 팔로워수 변화

	public FollowLog() {
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getFollowingId() {
		return followingId;
	}

	public void setFollowingId(String followingId) {
		this.followingId = followingId;
	}

	public String getFollowerId() {
		return followerId;
	}

	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getTotalExerciseCnt() {
		return totalExerciseCnt;
	}

	public void setTotalExerciseCnt(String totalExerciseCnt) {
		this.totalExerciseCnt = totalExerciseCnt;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getCntChange() {
		return cntChange;
	}

	public void setCntChange(String cntChange) {
		this.cntChange = cntChange;
	}

	@Override
	public String toString() {
		return "FollowLog [UserId=" + UserId + ", followingId=" + followingId + ", followerId=" + followerId
				+ ", nickName=" + nickName + ", totalExerciseCnt=" + totalExerciseCnt + ", profileImg=" + profileImg
				+ ", cntChange=" + cntChange + "]";
	}

}
