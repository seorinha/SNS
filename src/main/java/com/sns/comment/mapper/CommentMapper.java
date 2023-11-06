package com.sns.comment.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper {

	//댓글작성 
	public void insertComment(
			@Param("postId") int postId,
			@Param("userId") int userId,
			@Param("content") String content);
	
	public List<Comment> selectCommentListByPostId(int postId);
	
}
