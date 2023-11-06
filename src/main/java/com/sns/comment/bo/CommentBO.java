package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.domain.CommentView;
import com.sns.comment.mapper.CommentMapper;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserBO userBO;
	
	//댓글작성
	//input: postId, userId, content
	//output: x
	public void addComment(int postId, int userId, String content) {
		commentMapper.insertComment(postId, userId, content);
	}

	//input: 글 번호
	//output: List<CommentView>
	public List<CommentView> generateCommentViewListByPostId(int postId);
		List<CommentView> commentView = new ArrayList<>();
		
		//글에 해당하는 댓글들 목록 가져오기ㅣ List<Comment>
		List<Comment> commentList = commentMapper.selectCommentListByPost
		//반복문 순회
		//List<Comment> -> List<CommentView>
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();
			
			//댓글내용 담기
			commentView.setComment(comment);
			
			//댓글쓴이 내용 담기
			UserEntity user = userBO.getUserEntityByLoginId(comment.getUserId());
			commentView.setUser(user);
			
			
			//리스트에 담는다
			commentViewList.add(commentView);
		}
				
		return commentViewList;
}
