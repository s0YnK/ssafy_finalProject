package com.ssafy.cgmg.model.service;

import java.util.List;

import com.ssafy.cgmg.model.dto.Notify;

public interface NotifyService {
	
	// 알림 목록 조회
	List<Notify> getNotifyList(String userId);

}
