package com.ssafy.cgmg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.cgmg.model.dto.ExerciseLog;
import com.ssafy.cgmg.model.service.ExerciseLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/exercise-api")
@Api(tags="운동 컨트롤러")
public class ExerciseLogRestController {

	@Autowired
	private ExerciseLogService exerciseLogService;
	
	// 랭킹 조회
	@GetMapping("/rank/{part}")
	@ApiOperation(value="파트별 랭킹 조회", notes="'total'을 보내면 전체 랭킹 조회")
	public ResponseEntity<List<ExerciseLog>> rank(@PathVariable("part") String bodyPart) {
		List<ExerciseLog> list = exerciseLogService.getRank(bodyPart);
		return new ResponseEntity<List<ExerciseLog>>(list, HttpStatus.OK);
	}
	
	// 총 운동 횟수 조회
	@GetMapping("/exercise/log/total")
	@ApiOperation(value="총 운동 횟수 조회", notes="티어 계산을 위해")
	public ResponseEntity<ExerciseLog> totalCnt(String userId){
		ExerciseLog totalCnt = exerciseLogService.getTotalCnt(userId);
		return new ResponseEntity<ExerciseLog>(totalCnt, HttpStatus.OK);
	}
	
	// 부위별 운동 횟수 조회
	@GetMapping("/exercise/log/part")
	@ApiOperation(value="부위별 운동 횟수 조회", notes="육각형 그래프에 표시")
	public ResponseEntity<List<ExerciseLog>> countByBodyPart(String userId){
		List<ExerciseLog> list = exerciseLogService.getCntByPart(userId);
		return new ResponseEntity<List<ExerciseLog>>(list, HttpStatus.OK);
	}
	
	// 날짜별, 운동별 운동 횟수 조회
	@GetMapping("/exercise/log")
	@ApiOperation(value="날짜별, 운동별 운동 횟수 조회", notes="스트릭 현황 표시 위해. 여기서만 cnt는 운동한 세트수")
	public ResponseEntity<List<ExerciseLog>> countByDateAndExercise(String userId){
		List<ExerciseLog> list = exerciseLogService.getCntByDateAndExercise(userId);
		return new ResponseEntity<List<ExerciseLog>>(list, HttpStatus.OK);
	}
	
	// 운동 기록 추가
	@PostMapping("/exercise/log")
	@ApiOperation(value="운동 기록 추가", notes="운동할 때마다 시행한 세트수 포함")
	public ResponseEntity<Void> insertExerciseLog(@RequestBody ExerciseLog exerciseLog) {
		exerciseLogService.writeExerciseLog(exerciseLog);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

}
