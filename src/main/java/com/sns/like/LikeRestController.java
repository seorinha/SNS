package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

@RestController
public class LikeRestController {

	@Autowired
	private LikeBO likeBO;
	
	/**
	 *좋아요 누르기 해제하기 
	 * @param postId
	 * @param session
	 * @return
	 */
	//get: /like?postId=13   @RerquestParam("postId")
	//get: /like/13          @PathVariable
	@RequestMapping("/like/{postId}")
	public Map<String, Object> likeToggle(
			@PathVariable int postId,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		
		//로그인 여부를 확인 
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("code", 500);
			result.put("errorMessage", "로그인을 해주세요");
			
			return result;
		}
		
		//로그인이 돼있으면 bo호출-> like 여부 체크 
		likeBO.likeTobble(postId, userId);
		
		//응답값
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
	
	//좋아요 개수
}
