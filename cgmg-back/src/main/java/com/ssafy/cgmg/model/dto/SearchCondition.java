package com.ssafy.cgmg.model.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "검색 DTO")
public class SearchCondition {
	private String key = "none"; // 검색할 칼럼명
	private String word; // 검색할 키워드
	private String orderBy = "none"; // 검색 결과의 정렬 기준 칼럼명
	private String orderByDir; // 검색 결과의 정렬 방향

	public SearchCondition() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderByDir() {
		return orderByDir;
	}

	public void setOrderByDir(String orderByDir) {
		this.orderByDir = orderByDir;
	}

}
