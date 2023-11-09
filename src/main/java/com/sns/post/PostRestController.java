package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;

@RequestMapping("/post")
@RestController
public class PostRestController {

	@Autowired
	private PostBO postBO;
	
	/**
	 * 타임라인에 글 쓰기
	 * @param content
	 * @param file
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> create(
				@RequestParam("content") String content,
				@RequestParam("file") MultipartFile file,
				HttpSession session) {
			
			Integer userId = (Integer)session.getAttribute("userId");
			String userLoginId = (String)session.getAttribute("userLoginId");
			
			
			//응답값
			Map<String, Object> result = new HashMap<>();
			if (userId == null) {
				result.put("code", 500); //비로그인
				result.put("result", "error");
				result.put("errorMessage", "로그인을 해주세요");
				
				return result;
			}
			
			postBO.addPost(userId, userLoginId, content, file);
			
			result.put("code", 200);
			result.put("result", "성공");
			
			return result;
		}
	
	
	//글 삭제하기
	@DeleteMapping("/delete")
	public Map<String, Object> delete(
			@RequestParam("postId") int postId,
			HttpSession session) {
		
		//로그인 여부 확인
		int userId = (int)session.getAttribute("userId");
		
		//삭제
		postBO.deletePostByPostId(postId, userId);
		
		//응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
}
