package com.sns.post.bo;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.mapper.CommentMapper;
import com.sns.common.FileManagerService;
import com.sns.like.mapper.LikeMapper;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

@Service
public class PostBO {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostRepository postRepository;	

	@Autowired
	private FileManagerService fileManager;
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private LikeMapper likeMapper;
	
	//타임라인 글 목록 뿌리기
	// input: X     
	//output: List<PostEntity>
	public List<PostEntity> getPostList() {
		return postRepository.findAllByOrderByIdDesc();
	}
	
	//글쓰기 submit 
	//input: 파라미터들(userId, content, file)
	//output: x
	public PostEntity addPost(int userId, String userLoginId, String content, MultipartFile file) {
		String imagePath = null;
		
		// 이미지가 있으면 업로드 
		if (file != null) {
			imagePath = fileManager.saveFile(userLoginId, file);
		}
		
		return postRepository.save(
				PostEntity.builder()
				.userId(userId)
				.content(content)
				.imagePath(imagePath)
				.build());
	}
	
	
	//글 삭제
	@Transactional // 하나라도 실패하면 실패로 보는, 원복이 되는 
	public void deletePost(int postId) {
		//기존 글 가져오기
		PostEntity post = postRepository.selectPostById(postId);
		if (post == null) {
			logger.info("[글 삭제] post가 null. postId:{}", postId);
			return;
		}
		
		//기존 이미지 존재한다면 삭제
		if (post.getImagePath() != null) {
			fileManager.deleteFile(post.getImagePath());
		}
		
		//db글 삭제
		postRepository.deletePost(postId);
		
		//db 좋아요 삭제
		likeMapper.deleteLikeByPostId(postId);
		
		//db 댓글 삭제
		commentMapper.deleteCommentByPostId(postId);
	}
	
}