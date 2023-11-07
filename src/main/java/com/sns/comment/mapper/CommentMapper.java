package com.sns.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.comment.domain.Comment;

@Repository
public interface CommentMapper {

	//댓글작성 
	public void insertComment(
			@Param("postId") int postId,
			@Param("userId") int userId,
			@Param("content") String content);
	
	
	//댓글 뿌리기
	public List<Comment> selectCommentListByPostId(int postId);
	
	//댓글 삭제하기
	public void deleteCommentById(int id); 
	
}
