package com.ssafy.cgmg.model.dao;

import java.util.List;

import com.ssafy.cgmg.model.dto.ExerciseLog;
import com.ssafy.cgmg.model.dto.SearchCondition;

public interface ExerciseLogDao {
	
		// 랭킹 조회
		public List<ExerciseLog> selectRank(String bodyPart);
		
		// 특정 users의 전체기간 동안 총 운동 횟수. 티어 계산을 위한
		public ExerciseLog selectTotalCnt(String userId);
		
		// 전체기간동안 부위별 운동한 횟수. 운동 balance 육각형 그래프에 표시 위해
		public List<ExerciseLog> selectCntByPart(String userId);

		// 전체기간 동안 날짜별, 운동별 운동 횟수. 스트릭 현황 표시 위해
		public List<ExerciseLog> selectCntByDateAndExercise(String userId);
		
		// 어제 운동 유무 조회. 연속 스트릭 업데이트 판단 위해
		public List<ExerciseLog> selectCntYesterday(String userId);
		
		// 오늘 운동 유무 조회. 연속 스트릭 업데이트 판단 위해
		public List<ExerciseLog> selectCntToday(String userId);
		
		// 연속 스트릭 카운트해서 업데이트
		public int updateContinuedStreak(String userId, String streak);
		
		// 운동 기록 작성
		public int insertExerciseLog(ExerciseLog exerciseLog);
		
		// 총 운동 횟수 프로필에 업데이트
		public int updateTotalExerciseCnt(String userId);
		
}
