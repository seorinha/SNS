package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.CommentView;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.domain.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;


@Service
public class TimelineBO {

	@Autowired
	private PostBO postBO; //timelineBO가 postBO를 부른다
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	//댓글 뿌리기
	//input : userId()
	//output: List<cardView>
	public List<CardView> generateCardViewList() { //화면용으로 가공할 때는 보통 generate 사용
		List<CardView> cardViewList = new ArrayList<>(); // [] 비어있는 리스트
		
		//글 목록을 가져온다 List<PostEntity>
		List<PostEntity> postList = postBO.getPostList();
		
		//글목록 반복문 순회 
		//postEntity -> CardView  -> cardViewList에 담는다     
		for (PostEntity post : postList) { // 글이 3개일 때 : 0 1 2 
			//post 하나에 대응되는 하나의 카드를 만든다
			CardView cardView = new CardView();
			
			//글 1개
			cardView.setPost(post);
			
			//글쓴이 정보세팅 userbo에게 id로 조회하겟다고 말함
			UserEntity user = userBO.getUserEntityById(post.getUserId());
			cardView.setUser(user);
			
			
			//댓글들
			List<CommentView> commentList = commentBO.generateCommentViewListByPostId(post.getId());
			cardView.setCommentList(commentList);                                                                                                                                                                                                                                     
			
			//좋아요 카운트 = like table 에서 postId에 대해서 count가 몇개인지 
			int likeCount = likeBO.getLikeCountByPostId(post.getId());
			cardView.setLikeCount(likeCount);
			
			//내가 좋아요 눌럿는지 여부
			// false가 되는 경우: 비로그인 이나 좋아요를 누르지 않았을 때
			boolean filledLike = likeBO.filledLike(post.getId(), userId);
			cardView.setFilledLike(filledLike);
			
			
			//★★★★★ 마지막에 cardViewList에 card를 넣는다
			cardViewList.add(cardView);
		}
		
		
		return cardViewList;
	}
}
