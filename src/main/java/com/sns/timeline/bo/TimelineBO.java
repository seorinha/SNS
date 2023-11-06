package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.CommentView;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.domain.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class TimelineBO {

	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	//댓글 뿌리기
	//input : x
	//output: List<cardView>
	public List<CardView> generateCardViewList() {
		List<CardView> cardViewList = new ArrayList<>(); // [] 비어있는 리스트
		
		//글 목록을 가져온다 List<PostEntity>
		List<PostEntity> postList = postBO.getPostList();
		
		//글목록 반복문 순회 
		//postEntity -> CardView  -> cardView에 담는다     
		for (PostEntity post : postList) { // 글이 3개일 때 : 0 1 2 
			//post 하나에 대응되는 하나의 카드를 만든다
			CardView card = new CardView();
			
			//글 1개
			cardView.setPost(post);
			
			//글쓴이 정보세팅
			UserEntity user = userBO.getUserEntityById(post.getUserId());
			cardView.setUser();
			
			
			//댓글들
			List<CommentView> commentList = commentBO.generateCommentViewListByPostId(post.getId());
			cardView.  
			
			//좋아요 카운트
			
			//내가 좋아요 눌럿는지 여부

			
			
			//★★★★★ 마지막에 cardViewList에 card를 넣는다
			cardViewList.add(cardView);
		}
		
		
		return cardViewList;
	}
}
