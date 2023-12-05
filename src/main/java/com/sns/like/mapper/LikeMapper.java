package com.sns.like.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeMapper {

//	//좋아요 누르기 해제하기
//	public int selectLikeCountByPostIdUserId(
//			@Param("postId") int postId,
//			@Param("userId") int userId);
//	//좋아요 갯수
//	public int selectLikeCountByPostId(int postId);
	
	//위의 두가지 메소드를 합친 것
	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId,
			@Param("userId") Integer userId);
	
	
	public void insertLike(
			@Param("postId") int postId,
			@Param("userId") int userId);

	
	public void deleteLikeByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	//글삭제 좋아요삭제
	public void deleteLikeByPostId(int postId);
	
}
