package com.ssafy.cgmg.model.service;

import java.util.List;

import com.ssafy.cgmg.model.dto.FollowLog;
import com.ssafy.cgmg.model.dto.User;

public interface UserService {

	// 회원 가입
	int signup(User user);

	// 로그인
	User login(User user);

	// 회원 정보 조회
	User getUser(String userId);
	
	// 회원 목록 조회
	List<User> getUserList();

	// 회원 정보 수정
	int modifyUser(User user);

	// 회원 탈퇴
	int removeUser(String userId);
	
	// 회원 프로필 조회
	User getProfile(String userId);
	
	// userId로 회원 검색. 팔로우 위해 검색 시 사용
	List<User> searchByUserId(String word);
	
	// 내가 팔로우한 회원 목록 조회
	List<FollowLog> getFollowingList(String userId);
	
	// 나를 팔로우한 회원 목록 조회
	List<FollowLog> getFollowerList(String userId);
	
	// 팔로우 등록
	int writeFollowId(FollowLog followLog);
	
	// 팔로우 취소
	int removeFollowId(FollowLog followLog);
}