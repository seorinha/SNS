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
	public void likeToggle(int postId, int userId) {
		//셀렉트 -> count(*)
		if (likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) > 0) {
			//삭제
			likeMapper.deleteLikeByPostIdUserId(postId, userId);
		} else {
			//추가
			likeMapper.insertLike(postId, userId);
		}
	}
	
	
	//좋아요 갯수
	//input: 글번호
	//output: 개수(int)
	public int getLikeCountByPostId(int postId) {
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, null);
	}
	
	
	//input: 글번호 , userId(Integer)
	//output: 채워졌는지 여부(boolean)
	public boolean filledLike(int postId, Integer userId) {
		//비로그인
		if (userId == null) {
			return false;
		}
		
		//로그인
		//0보다 큰 경우 있음(채워진 하트 true), 그렇지 않으면 false(빈하트)
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) > 0;
		
	}
	
	//좋아요 삭제
	public void deleteLikeByPostId(int postId) {
		likeMapper.deleteLikeByPostId(postId);
	}
	
}
