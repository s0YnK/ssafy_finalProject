package com.ssafy.cgmg.model.dao;

import java.util.List;

import com.ssafy.cgmg.model.dto.Notify;

public interface NotifyDao {
	
	// 알림 등록
	public int insertNotify(Notify notify);
	
	// 알림 목록 조회
	public List<Notify> selectNotifyList(String userId);
	
	// 회원탈퇴 시 알림 기록 삭제
	public int deleteAllNotify(String userId);

}
