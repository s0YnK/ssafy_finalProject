package com.ssafy.cgmg.model.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "회원 정보 DTO")
public class User {
	private String userId; // 회원 id
	private String password; // 비밀번호
	private String name; // 이름
	private String nickName; // 닉네임
	private String profileImg; // 프로필 이미지 경로
	private String email; // 이메일
	private String googleAccount; // 구글계정 (구글 로그인 등록한 경우)
	private int totalExerciseCnt; // 총 운동 횟수 -> 티어로 변환
	private int continuedStreak; // 연속 운동일수
	private int followingCnt; // 팔로잉수
	private int followerCnt; // 팔로워수

	public User() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGoogleAccount() {
		return googleAccount;
	}

	public void setGoogleAccount(String googleAccount) {
		this.googleAccount = googleAccount;
	}

	public int getTotalExerciseCnt() {
		return totalExerciseCnt;
	}

	public void setTotalExerciseCnt(int totalExerciseCnt) {
		this.totalExerciseCnt = totalExerciseCnt;
	}

	public int getContinuedStreak() {
		return continuedStreak;
	}

	public void setContinuedStreak(int continuedStreak) {
		this.continuedStreak = continuedStreak;
	}

	public int getFollowingCnt() {
		return followingCnt;
	}

	public void setFollowingCnt(int followingCnt) {
		this.followingCnt = followingCnt;
	}

	public int getFollowerCnt() {
		return followerCnt;
	}

	public void setFollowerCnt(int followerCnt) {
		this.followerCnt = followerCnt;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", nickName=" + nickName
				+ ", profileImg=" + profileImg + ", email=" + email + ", googleAccount=" + googleAccount
				+ ", totalExerciseCnt=" + totalExerciseCnt + ", continuedStreak=" + continuedStreak + ", followingCnt="
				+ followingCnt + ", followerCnt=" + followerCnt + "]";
	}

}
