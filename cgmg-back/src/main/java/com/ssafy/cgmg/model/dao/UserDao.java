package com.ssafy.cgmg.model.dao;

import java.util.List;

import com.ssafy.cgmg.model.dto.FollowLog;
import com.ssafy.cgmg.model.dto.User;

public interface UserDao {

	// 회원 정보 등록
	int insertUser(User user);
	
	// 회원 정보 조회
	User selectOne(String UserId);
	
	// 회원 목록 조회
	List<User> selectAll();
	
	// 회원 정보 수정
	int updateUser(User user);
	
	// 회원 정보 삭제
	int deleteUser(String userId);

	// 회원 프로필 조회
	User selectProfile(String userId);
	
	// userId로 회원 검색. 팔로우 위해 검색 시 사용
	List<User> searchByUserId(String word); 
	
	// 내가 팔로우 하는 회원 목록 조회
	List<FollowLog> selectFollowingList(String userId);
	
	// 나를 팔로우 하는 회원 목록 조회
	List<FollowLog> selectFollowerList(String userId);
	
	// 팔로우 등록
	int insertFollowId(FollowLog followLog);
	
	// 팔로우 삭제
	int deleteFollowId(FollowLog followLog);
	
	// 팔로잉수 업데이트
	int updateFollowingCnt(FollowLog followLog);
	
	// 팔로워수 업데이트
	int updateFollowerCnt(FollowLog followLog);
}