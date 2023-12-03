package com.ssafy.cgmg.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.cgmg.model.dao.NotifyDao;
import com.ssafy.cgmg.model.dto.Notify;

@Service
public class NotifyServiceImpl implements NotifyService {
	
	@Autowired
	NotifyDao notifyDao;

	@Override
	public List<Notify> getNotifyList(String userId) {
		return notifyDao.selectNotifyList(userId);
	}

}
