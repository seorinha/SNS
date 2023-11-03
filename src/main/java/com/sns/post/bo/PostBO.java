package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

@Service
public class PostBO {

	@Autowired
	private PostRepository postRepository;	

	//타임라인 글 목록 뿌리기
	// input: X     
	//output: List<PostEntity>
	public List<PostEntity> getPostList() {
		return postRepository.findAllByOrderByIdDesc();
	}
	
	//글쓰기 submit 
	//input: 파라미터들(userId, content, file)
	//output: x
	public void addPost(String userId, String content, MultipartFile file) {
		String imagePath = null;
	}
	
	
}