package com.sns.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sns.post.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {
	
	//타임라인 글 목록 뿌리기
	public List<PostEntity> findAllByOrderByIdDesc();
	

	
	
}
