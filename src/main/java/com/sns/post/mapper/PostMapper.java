package com.sns.post.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMapper {

	public List<Map<String, Object>> selectPostList();
	
	public void insertPost(
			@Param("userId") String userId,
			@Param("content") String content,
			@Param("imagePath") String imagePath);
}
