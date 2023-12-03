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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.cgmg.model.dto.Comment;
import com.ssafy.cgmg.model.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/comment-api")
@Api(tags="댓글 컨트롤러")
@CrossOrigin("*")
public class CommentRestController {

	@Autowired
	private CommentService commentService;
	
	// 댓글 조회
	@GetMapping("/comment/list/{id}")
	@ApiOperation(value="댓글 목록 조회")
	public ResponseEntity<?> list(@PathVariable("id") int postId){
		List<Comment> list = commentService.getCommentList(postId);
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
	}
	
	// 댓글 등록
	@PostMapping("/comment")
	@ApiOperation(value="댓글 등록")
	public ResponseEntity<Void> write(@RequestBody Comment comment){
		commentService.writeComment(comment);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	// 댓글 수정
	@PutMapping("/comment")
	@ApiOperation(value="댓글 수정")
	public ResponseEntity<Void> update(@RequestBody Comment comment){
		commentService.modifyComment(comment);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 댓글 삭제
	@DeleteMapping("/comment")
	@ApiOperation(value="댓글 삭제")
	public ResponseEntity<Void> delete(@RequestParam int id){
		commentService.removeComment(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
