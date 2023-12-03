package com.ssafy.cgmg.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.cgmg.model.dao.BoardDao;
import com.ssafy.cgmg.model.dao.CommentDao;
import com.ssafy.cgmg.model.dao.NotifyDao;
import com.ssafy.cgmg.model.dao.UserDao;
import com.ssafy.cgmg.model.dto.Board;
import com.ssafy.cgmg.model.dto.Comment;
import com.ssafy.cgmg.model.dto.Notify;
import com.ssafy.cgmg.model.dto.User;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private NotifyDao notifyDao;

	@Autowired
	private BoardDao boardDao;

	@Autowired
	private UserDao userDao;

	@Override
	public List<Comment> getCommentList(int postId) {
		// 부모댓글id 기준 오름차순으로 정렬된 댓글 리스트 가져오기
		List<Comment> list = commentDao.selectComment(postId);

		Map<Integer, Comment> map = new HashMap<>(); // (댓글id, 댓글 객체)
		List<Comment> result = new ArrayList<>(); // 반환할 댓글 리스트

		for (int i = 0; i < list.size(); i++) {
			Comment comment = list.get(i); // i번째 댓글 객체 가져오기
			map.put(comment.getId(), comment); // 댓글을 map에 넣기. (댓글id, 댓글 객체)

			// 부모 댓글이 있을 경우
			if (comment.getParentId() != 0) {
				Comment parent = map.get(comment.getParentId());
				if (parent != null) {
					parent.getChildren().add(comment);
				} else {
					continue;
				}
			} else {
				// 부모 댓글이 없으면 바로 result에 추가
				result.add(comment);
			}
		}

		return result;
	}

	@Override
	public int writeComment(Comment comment) {
		Board board = boardDao.selectOne(comment.getPostId());
		User user1 = userDao.selectOne(comment.getWriter());
		User user2 = userDao.selectOne(board.getWriter());
		System.out.println(user1.toString());
		System.out.println(user2.toString());

		if (!user1.getUserId().equals(user2.getUserId())) {
			Notify notify = new Notify();
			String message = user1.getNickName() + "님이 " + user2.getNickName() + "님의 글에 <br>댓글을 남겼습니다.";

			notify.setUserId(board.getWriter());
			notify.setCausedBy(comment.getWriter());
			notify.setPostId(comment.getPostId());
			notify.setMessage(message);
			notify.setNotType("comment");
			notifyDao.insertNotify(notify);
		}
		
		return commentDao.insertComment(comment);
	}

	@Override
	public int modifyComment(Comment comment) {
		return commentDao.updateComment(comment);
	}

	@Override
	public void removeComment(int id) {
		// 삭제하려는 댓글 조회
		Comment comment = commentDao.selectOne(id);

		// 자식 댓글 리스트 가져오기
		List<Comment> children = commentDao.selectChildren(comment.getId());

		// 자식 댓글이 없으면
		if (children.size() == 0) {
			commentDao.deleteComment(comment.getId()); // 댓글 삭제하고
			removeDeletedParent(comment); // 삭제상태인 부모 댓글 삭제하는 메서드 호출
		} else {
			comment.setIsDeleted("true");
			commentDao.updateIsDeleted(comment);
		}
	}

	public void removeDeletedParent(Comment comment) {

		// 부모 댓글
		Comment parent;

		// 부모 댓글 있으면 가져오기
		if (comment.getParentId() != 0) {
			parent = commentDao.selectOne(comment.getParentId());
		} else { // 없으면 종료
			return;
		}

		// 부모 댓글의 자식 댓글 가져오기
		List<Comment> childrenOfParent = commentDao.selectChildren(parent.getId());

		// 부모 댓글이 삭제 상태이고, 부모 댓글의 자식 댓글이 0개이면
		if ("true".equals(parent.getIsDeleted()) && childrenOfParent.size() == 0) {
			commentDao.deleteComment(parent.getId()); // 부모 댓글 삭제하고
			removeDeletedParent(parent); // 재귀함수 호출
		} else { // 아니면 종료
			return;
		}

	}

}
