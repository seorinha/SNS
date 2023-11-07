package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
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
	//userRepository 부를 수 없다, bo는 남의 것을 부를때는 bo만 불러야한다
	//postbo는 postRepository만 부를 수 있고 userBO는 userRepository만 부를 수 있다
	
	//댓글작성
	//input: postId, userId, content
	//output: x
	public void addComment(int postId, int userId, String content) {
		commentMapper.insertComment(postId, userId, content);
	}

	
	//input:글 번호
	//output: List<CommentView>
	public List<CommentView> generateCommentViewListByPostId(int postId) {
		
		List<CommentView> commentViewList = new ArrayList<>();  //[] 비어잇음
		
		//글에 해당하는 댓글들(List<Comment>) 목록 가져오기 
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		//반복문 순회
		//List<Comment> -> List<CommentView>
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();
			
			//댓글 내용 담기
			commentView.setComment(comment);
			
			//댓글쓴이 내용 담기
			UserEntity user = userBO.getUserEntityById(comment.getUserId());
			commentView.setUser(user);
			
			//리스트에 담는다
			commentViewList.add(commentView);
		}
		return commentViewList;
	}
	
	//댓글 삭제하기
	//input: 삭제할 댓글 번호
	//output:x
	public void deleteCommentById(int id) {
		commentMapper.deleteCommentById(id);
	}
	
	
}
