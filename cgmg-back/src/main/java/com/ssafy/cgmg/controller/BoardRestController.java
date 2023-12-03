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
import com.ssafy.cgmg.model.dto.SearchCondition;
import com.ssafy.cgmg.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/board-api")
@Api(tags="게시판 컨트롤러")
@CrossOrigin("*")
public class BoardRestController {

	@Autowired
	private BoardService boardService;

	// 게시글 전체 목록 조회
	@GetMapping("/board/list")
	@ApiOperation(value="게시글 전체 목록 조회")
	public ResponseEntity<?> list(){
		List<Board> list = boardService.getList();
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
	}
	
	// 게시글 상세 조회
	@GetMapping("/board/{id}")
	@ApiOperation(value="게시글 상세 조회")
	public ResponseEntity<Board> detail(@PathVariable int id){
		Board board = boardService.getBoard(id);
		return new ResponseEntity<Board>(board, HttpStatus.OK);
	}
	
	// 게시글 등록
	@PostMapping("/board")
	@ApiOperation(value="게시글 등록")
	public ResponseEntity<Void> write(@RequestBody Board board){
		boardService.writeBoard(board);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	// 게시글 수정
	@ApiOperation(value="게시글 수정")
	@PutMapping("/board")
	public ResponseEntity<Void> update(@RequestBody Board board){
		boardService.modifyBoard(board);
			
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 게시글 삭제
	@DeleteMapping("/board/{id}")
	@ApiOperation(value="게시글 삭제")
	public ResponseEntity<Void> delete(@PathVariable int id){
		boardService.removeBoard(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 게시글 검색
	@GetMapping("/board/search")
	@ApiOperation(value="게시글 검색")
	public ResponseEntity<?> search(SearchCondition searchCondition){
		List<Board> list = boardService.search(searchCondition);
		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
	}
	
	// 내가 작성한 게시글 목록 조회
	@GetMapping("/board/mylist")
	@ApiOperation(value="내가 쓴 게시글 목록 조회")
	public ResponseEntity<List<Board>> boardListByUserId(String userId){
		List<Board> list = boardService.getListByUserId(userId);
		return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
	}
	
	// 게시글 좋아요 등록
	@PostMapping("/board/like")
	@ApiOperation(value="게시글 좋아요 등록")
	public ResponseEntity<Void> insertLikeLog(@RequestBody LikeLog likeLog){
		boardService.writeLikeLog(likeLog);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	// 게시글 좋아요 취소
	@DeleteMapping("/board/like")
	@ApiOperation(value="게시글 좋아요 취소")
	public ResponseEntity<Void> deleteLikeLog(@RequestBody LikeLog likeLog){
		boardService.removeLikeLog(likeLog);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	

	// 게시글 좋아요 기록 조회
	@GetMapping("/board/like/log")
	@ApiOperation(value="게시글 좋아요 기록 조회", notes="해당 회원이 해당 게시글을 좋아요했는지 조회")
	public ResponseEntity<Boolean> getLikeLog(LikeLog likeLog) {
		boolean result = boardService.getLikeLog(likeLog);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	// 좋아요한 게시글 목록 조회
	@GetMapping("/board/like")
	@ApiOperation(value="좋아요한 게시글 목록 조회")
	public ResponseEntity<List<Board>> likedBoardList(String userId){
		List<Board> list = boardService.getLikedBoard(userId);
		return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
	}
	
}
