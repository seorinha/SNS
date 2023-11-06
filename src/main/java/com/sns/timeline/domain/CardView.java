package com.sns.timeline.domain;

import java.util.List;

import com.sns.comment.domain.CommentView;
import com.sns.post.entity.PostEntity;
import com.sns.user.entity.UserEntity;

import lombok.Data;

//View용 객체
//글 1개와 mapping된다
@Data
public class CardView {

	//글 1개 정보
	private PostEntity post;
	
	//글쓴이 정보
	private UserEntity user;
	
	//댓글들
	private List<CommentView> commentList;
	
	//좋아요 개수
	
	
	//내가 좋아요를 눌럿는지 여부
	

}
