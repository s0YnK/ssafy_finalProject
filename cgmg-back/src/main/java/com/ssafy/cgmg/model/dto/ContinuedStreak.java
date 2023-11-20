package com.ssafy.cgmg.model.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(value="연속 스트릭 일수 업데이트 DTO")
public class ContinuedStreak {

	private String userId;
	private String streak;
	
	public ContinuedStreak() {	
	}
	
	public ContinuedStreak(String userId, String streak) {
		this.userId = userId;
		this.streak = streak;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStreak() {
		return streak;
	}

	public void setStreak(String streak) {
		this.streak = streak;
	}

	@Override
	public String toString() {
		return "continuedStreak [userId=" + userId + ", streak=" + streak + "]";
	}
	
}
