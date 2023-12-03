package com.ssafy.cgmg.model.service;

import java.util.List;

import com.ssafy.cgmg.model.dto.ExerciseLog;

public interface ExerciseLogService {
	
	// 전체 랭킹 or 파트별 랭킹 조회
	List<ExerciseLog> getRank(String bodyPart);
	
	// 전체 기간 동안 총 운동 횟수 조회. 티어 계산을 위한
	ExerciseLog getTotalCnt(String userId);
	
	// 전체 기간 동안 부위별 운동한 횟수. 운동 balance 그래프에 표시 위해
	List<ExerciseLog> getCntByPart(String userId);
	
	// 전체 기간 동안 날짜별, 운동별 운동 횟수. 스트릭 표시 위해 
	List<ExerciseLog> getCntByDateAndExercise(String userId);

	// 운동 기록 작성
	int writeExerciseLog(ExerciseLog exerciseLog);

}
