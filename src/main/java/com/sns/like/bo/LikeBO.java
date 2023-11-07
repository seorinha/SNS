package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.mapper.LikeMapper;

@Service
public class LikeBO {

	@Autowired
	private LikeMapper likeMapper;
	
	//좋아요 누르기, 해제하기
	//input:누가, 어떤 글에 
	//output:x
	public void likeTobble(int postId, int userId) {
		//셀렉트 -> count(*)
		if (likeMapper.selectLikeCountByPostIdUserId(postId, userId) > 0) {
			//삭제
			likeMapper.deleteLikeByPostIdUserId(postId, userId);
		} else {
			//추가
			likeMapper.insertLike(postId, userId);
		}
	}
	
}
