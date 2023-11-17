package com.ssafy.cgmg.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.cgmg.model.dao.ExerciseLogDao;
import com.ssafy.cgmg.model.dao.UserDao;
import com.ssafy.cgmg.model.dto.ExerciseLog;
import com.ssafy.cgmg.model.dto.FollowLog;
import com.ssafy.cgmg.model.dto.User;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	UserDao userDao;
	
	@Autowired
	ExerciseLogDao exerciseLogDao;
	
	@Override
	public int signup(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public User login(User user) {
		User tmp = userDao.selectOne(user.getUserId());
		if(tmp != null && tmp.getPassword().equals(user.getPassword())) {
			
			// 이 회원이 어제, 오늘 운동한 기록 가져오기
			String userId = tmp.getUserId();
			List<ExerciseLog> yesterday = exerciseLogDao.selectCntYesterday(userId);
			List<ExerciseLog> today = exerciseLogDao.selectCntToday(userId);
			
			// 어제, 오늘 운동한 기록이 없으면 
			if (yesterday.size() == 0 && today.size() == 0) {
				// 연속 스트릭 reset
				exerciseLogDao.updateContinuedStreak(userId, "reset");
			}
			
			// 회원 정보 반환
			return tmp;
		}
		return null;
	}

	@Override
	public User getUser(String id) {
		return userDao.selectOne(id);
	}
	
	@Override
	public List<User> getUserList() {
		return userDao.selectAll();
	}

	@Override
	public int modifyUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public int removeUser(String id) {
		return userDao.deleteUser(id);
	}
	
	@Override
	public User getProfile(String userId) {
		return userDao.selectProfile(userId);
	}

	@Override
	public List<User> searchByUserId(String word) {
		return userDao.searchByUserId(word);
	}

	@Override
	public List<FollowLog> getFollowingList(String userId) {
		return userDao.selectFollowingList(userId);
	}

	@Override
	public List<FollowLog> getFollowerList(String userId) {
		return userDao.selectFollowerList(userId);
	}

	@Override
	public int writeFollowId(FollowLog followLog) {
		followLog.setCntChange("up");
		userDao.updateFollowingCnt(followLog);
		userDao.updateFollowerCnt(followLog);
		return userDao.insertFollowId(followLog);
	}

	@Override
	public int removeFollowId(FollowLog followLog) {
		followLog.setCntChange("down");
		userDao.updateFollowingCnt(followLog);
		userDao.updateFollowerCnt(followLog);
		return userDao.deleteFollowId(followLog);
	}

}
