package com.ssafy.cgmg.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.cgmg.model.dao.CommentDao;
import com.ssafy.cgmg.model.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao commentDao;

	@Override
	public List<Comment> getCommentList(int postId) {
		return commentDao.selectComment(postId);
	}


	@Override
	public int writeComment(Comment comment) {
		return commentDao.insertComment(comment);
	}

	@Override
	public int modifyComment(Comment comment) {
		return commentDao.updateComment(comment);
	}

	@Override
	public void removeComment(Comment comment) {
		commentDao.deleteComment(comment.getId());
	}

}
