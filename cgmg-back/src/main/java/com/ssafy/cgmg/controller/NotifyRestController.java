package com.ssafy.cgmg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.cgmg.model.dto.Board;
import com.ssafy.cgmg.model.dto.LikeLog;
import com.ssafy.cgmg.model.dto.Notify;
import com.ssafy.cgmg.model.dto.SearchCondition;
import com.ssafy.cgmg.model.service.NotifyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/notify-api")
@Api(tags="알림 컨트롤러")
@CrossOrigin("*")
public class NotifyRestController {

	@Autowired
	private NotifyService notifyService;
	
	// 알림 목록 조회
	@GetMapping("/notify/list")
	@ApiOperation(value="알림 목록 조회")
	public ResponseEntity<?> notifyList(String userId){
		List<Notify> list = notifyService.getNotifyList(userId);
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Notify>>(list, HttpStatus.OK);
	}
	
}
