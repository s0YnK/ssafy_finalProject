package com.ssafy.cgmg.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.cgmg.model.dto.FollowLog;
import com.ssafy.cgmg.model.dto.User;
import com.ssafy.cgmg.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/user-api")
@Api(tags = "유저 컨트롤러")
public class UserRestController {

	@Autowired
	private UserService userService;

	@PostMapping("user")
	@ApiOperation(value = "회원 가입", response = User.class)
	public ResponseEntity<Integer> signup(@RequestBody User user) {
		int result = userService.signup(user);

		// result가 0 -> 등록 x
		//         1 -> 등록 o
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}

	// 로그인
	@PostMapping("login")
	@ApiOperation(value = "로그인")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		User tmp = userService.login(user);
		// 로그인 실패 시
		if (tmp == null)
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		
		User profile = userService.getProfile(tmp.getUserId());
		session.setAttribute("loginUser", tmp.getUserId());
		
		return new ResponseEntity<User>(profile, HttpStatus.OK);
	}

	// 로그아웃
	@GetMapping("logout")
	@ApiOperation(value = "로그아웃")
	public ResponseEntity<Void> logout(HttpSession session) {
		session.invalidate();
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 회원 목록 조회
	@GetMapping("user/list")
	@ApiOperation(value = "회원 목록 조회")
	public ResponseEntity<?> list() {
		List<User> list = userService.getUserList();
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	// 회원 정보 상세 조회
	@GetMapping("/user/detail")
	@ApiOperation(value = "회원 정보 상세", response = User.class)
	public ResponseEntity<User> detail(String id) {
		User user = userService.getUser(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// 회원 탈퇴
	@DeleteMapping("/user")
	@ApiOperation(value = "회원 탈퇴")
	public ResponseEntity<Integer> delete(@RequestBody String id) {
		int result = userService.removeUser(id);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	// 회원 정보 수정
	@PutMapping("/user")
	@ApiOperation(value = "회원 정보 수정")
	public ResponseEntity<Integer> update(@RequestBody User user) {
		int result = userService.modifyUser(user);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	// 회원 프로필 조회
	@GetMapping("/user/profile")
	@ApiOperation(value = "회원 프로필 조회")
	public ResponseEntity<User> userInfo(String userId) {
		User user = userService.getProfile(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	// 회원 검색
	@GetMapping("/user/search")
	@ApiOperation(value = "회원 검색")
	public ResponseEntity<List<User>> searchUser(String word) {
		List<User> list = userService.searchByUserId(word);
		return new ResponseEntity<List<User>> (list, HttpStatus.OK);
	}
	
	// 내가 팔로우한 회원 목록 조회
	@GetMapping("/user/list/following")
	@ApiOperation(value = "내가 팔로우한 회원 목록 조회")
	public ResponseEntity<List<FollowLog>> followingList(String userId) {
		List<FollowLog> list = userService.getFollowingList(userId);
		return new ResponseEntity<List<FollowLog>> (list, HttpStatus.OK);
	}
	
	// 나를 팔로우한 회원 목록 조회
	@GetMapping("/user/list/follower")
	@ApiOperation(value = "나를 팔로우한 회원 목록 조회")
	public ResponseEntity<List<FollowLog>> followerList(String userId) {
		List<FollowLog> list = userService.getFollowerList(userId);
		return new ResponseEntity<List<FollowLog>> (list, HttpStatus.OK);
	}
	
	// 팔로우 등록
	@PostMapping("/user/follow")
	@ApiOperation(value = "팔로우 등록")
	public ResponseEntity<Void> addFollow(@RequestBody FollowLog followLog) {
		userService.writeFollowId(followLog);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 팔로우 취소
	@DeleteMapping("/user/follow")
	@ApiOperation(value = "팔로우 취소")
	public ResponseEntity<Void> cancelFollow(@RequestBody FollowLog followLog) {
		userService.removeFollowId(followLog);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	

}