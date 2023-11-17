package com.ssafy.cgmg.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.cgmg.model.dao.ExerciseLogDao;
import com.ssafy.cgmg.model.dto.ExerciseLog;

@Service
public class ExerciseLogServiceImpl implements ExerciseLogService {
	
	@Autowired
	private ExerciseLogDao exerciseLogDao;

	@Override
	public List<ExerciseLog> getRank(String bodyPart) {
		return exerciseLogDao.selectRank(bodyPart);
	}

	@Override
	public ExerciseLog getTotalCnt(String userId) {
		return exerciseLogDao.selectTotalCnt(userId);
	}

	@Override
	public List<ExerciseLog> getCntByPart(String userId) {
		return exerciseLogDao.selectCntByPart(userId);
	}

	@Override
	public List<ExerciseLog> getCntByDateAndExercise(String userId) {
		return exerciseLogDao.selectCntByDateAndExercise(userId);
	}

	@Override
	public int writeExerciseLog(ExerciseLog exerciseLog) {
		String userId = exerciseLog.getUserId();
		
		// 이 회원이 오늘 운동한 기록 가져오기
		List<ExerciseLog> today = exerciseLogDao.selectCntToday(userId);
		
		// 오늘 첫 운동기록할 때 연속스트릭 일수 올리기
		if (today.size() == 0) {
			exerciseLogDao.updateContinuedStreak(userId, "plus");
		}

		// 티어 계산을 위한 총 운동횟수 올리기
		exerciseLogDao.updateTotalExerciseCnt(userId);
		
		return exerciseLogDao.insertExerciseLog(exerciseLog);
	}

}
