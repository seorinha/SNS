package com.sns.comment.domain;

import org.apache.jasper.compiler.Node.Comment;

import com.sns.user.entity.UserEntity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data //getter setter
//한개의 댓글
public class CommentView {

	//댓글 내용, 댓글쓴이 가 필요
	private Comment comment;
	
	private UserEntity user;
}
